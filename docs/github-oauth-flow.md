# GitHub OAuth + repository flow

هذا الملخّص يوضح كيف يتعامل الباك إند مع تسجيل/تسجيل دخول GitHub، وكيف يتعامل مع ربط GitHub لحساب موجود، وماذا يجب أن يفعل الفرونت إند في حالات نقص/بطلان التوكن.

## نقاط الدخول
- **بدء تدفق OAuth**: الطلب إلى `GET /api/v1/auth/github` مع باراميتر اختياري `mode`.
  - `mode=login` (الافتراضي): يستخدم لإنشاء حساب جديد/تسجيل دخول عبر GitHub.
  - `mode=link`: يستخدم عندما يكون المستخدم مسجّلاً في السيشن ويريد ربط GitHub بحسابه الحالي.
- **Callback GitHub**: تتم معالجته عبر Spring Security (المسار `/login/oauth2/code/github` افتراضياً) ويستخدم `CustomOAuth2UserService` لاستخراج بيانات GitHub وقراءة `mode`/`oauth_user_id` من السيشن.

## سلوك الباك إند
1. **تحديد الوضع (mode) عند البدء**
   - إذا كان `mode=link` والمستخدم مصادق عليه، يتم تخزين `oauth_mode=link` و`oauth_user_id` في الـ session، ثم التحويل إلى GitHub مع نفس الـ mode. وإلا يتم ضبط `oauth_mode=login` وإزالة أي `oauth_user_id`.【F:backend/src/main/java/com/spring/techpractica/ui/rest/controller/user/auth/oauth/OAuth2LoginController.java†L22-L40】

2. **معالجة الـ callback**
   - بعد عودة GitHub، يقرأ الـ service الـ session لتحديد إذا كان الطلب link أم login. إذا لم تتوفر بيانات link يعاد ضبط الوضع إلى `login` افتراضياً.【F:backend/src/main/java/com/spring/techpractica/infrastructure/security/oauth/config/CustomOAuth2UserService.java†L33-L56】
   - عند الحاجة يتم استرجاع البريد الأساسي عبر GitHub API (إذا كان البريد null).【F:backend/src/main/java/com/spring/techpractica/infrastructure/security/oauth/config/CustomOAuth2UserService.java†L47-L49】
   - يبني النظام أمر OAuth2Command ويمرره إلى use case المسؤول عن إنشاء/تحديث المستخدم.【F:backend/src/main/java/com/spring/techpractica/infrastructure/security/oauth/config/CustomOAuth2UserService.java†L51-L58】

3. **Link مقابل Login داخل use case**
   - **Link**: يجلب المستخدم الحالي بالـ `sessionUserId` ويخزن `githubAccessToken` و`githubConnected=true` و`providerId` فقط، دون إنشاء مستخدم جديد أو تعديل البريد/البيانات الأخرى.【F:backend/src/main/java/com/spring/techpractica/application/user/auth/oauth/HandleOAuth2LoginUseCase.java†L15-L23】
   - **Login/Register**: يبحث أولاً عن مستخدم بالـ providerId ثم بالبريد؛ في حال العثور يتم تحديثه بالتوكن وضبط الـ provider إلى GitHub وتعبئة البريد إذا كان فارغاً، وإلا ينشئ مستخدماً جديداً بهذه البيانات.【F:backend/src/main/java/com/spring/techpractica/application/user/auth/oauth/HandleOAuth2LoginUseCase.java†L25-L53】

4. **إنشاء Session/Repository**
   - عند استدعاء `POST /api/v1/sessions/` يقوم الـ use case بإنشاء السيشن ثم يحاول إنشاء مستودع GitHub باستخدام التوكن المخزن للمستخدم الحالي.【F:backend/src/main/java/com/spring/techpractica/application/session/create/CreateSessionUseCase.java†L35-L52】
   - التوكن يتم التحقق منه؛ إذا كان مفقوداً أو غير صالح يرمى استثناء `GitHubTokenInvalidException` بدلاً من إنشاء مستخدم جديد أو محاولة متابعة العملية.【F:backend/src/main/java/com/spring/techpractica/application/session/create/github/repo/CreateGithubRepositoryUseCase.java†L16-L22】
   - الاستثناء يُعاد كاستجابة HTTP 401 مع كود `GITHUB_TOKEN_INVALID` في الـ body ليسهل على الفرونت التعرّف على الحالة.【F:backend/src/main/java/com/spring/techpractica/ui/rest/shared/GlobalExceptionHandler.java†L89-L125】

## ما هو مطلوب من الفرونت إند
- **تسجيل/تسجيل دخول عبر GitHub من البداية**: زر "Login with GitHub" يوجّه إلى `/api/v1/auth/github` أو `/api/v1/auth/github?mode=login`. بعد العودة من GitHub يكون المستخدم مصادقاً وفي قاعدة البيانات مع توكنه.
- **ربط GitHub لمستخدم قائم**:
  1) عندما يكون المستخدم مسجلاً بحساب محلي/مزود آخر لكن بلا `githubAccessToken`، ويتم اكتشاف ذلك عند محاولة إنشاء Session/Repo، ستفشل الاستجابة بـ 401 و`code: "GITHUB_TOKEN_INVALID"`.
  2) على الفرونت، التقط هذا الكود وأعد توجيه المستخدم إلى `/api/v1/auth/github?mode=link` (مع الحفاظ على الـ redirect/local state لإعادة محاولة العملية لاحقاً).
  3) بعد اكتمال الـ callback سيُحفظ التوكن في حساب المستخدم الحالي ويمكن إعادة محاولة إنشاء السيشن/الريبو بنفس الطلب الأصلي.
- **حماية link mode**: إذا حاول الفرونت استدعاء `/api/v1/auth/github?mode=link` بدون أن يكون المستخدم مسجلاً الدخول، الباك إند سيعتبر التدفق كـ login عادي، لذا يجب التأكد من وجود جلسة قبل توجيه المستخدم إلى link mode.

## ملخص السلوك المتوقع
- لا يتم إنشاء مستخدم جديد أثناء link mode؛ فقط يتم إضافة توكن GitHub وربطه بالحساب الحالي.
- أخطاء نقص/بطلان التوكن تظهر دائماً كاستجابة 401 مع كود `GITHUB_TOKEN_INVALID`، وعلى الفرونت التعامل معها بإعادة توجيه المستخدم لتسجيل الدخول بـ GitHub (mode=link) ثم إعادة المحاولة.
- بعد ربط GitHub بنجاح، تستمر عمليات إنشاء السيشن/الريبو بشكل طبيعي باستخدام التوكن المخزن دون الحاجة لإعادة تسجيل المستخدم.
