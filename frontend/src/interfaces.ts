export type FormInputRegister =
  | "firstName"
  | "lastName"
  | "name"
  | "userEmail"
  | "userPassword";
interface IRegister {
  name: FormInputRegister;
  placeholder: string;
  type: string;
  label: string;
}
export const RegisterForm: IRegister[] = [
  {
    name: "firstName",
    type: "text",
    placeholder: "First Name",
    label: "First Name",
  },
  {
    name: "lastName",
    type: "text",
    placeholder: "Last Name",
    label: "Last Name",
  },
  {
    name: "name",
    type: "text",
    placeholder: "Username",
    label: "Username",
  },
  {
    name: "userEmail",
    type: "email",
    placeholder: "name@example.com",
    label: "Your email",
  },
  {
    name: "userPassword",
    type: "password",
    placeholder: "********",
    label: "Password",
  },
];
// export interface IErrorResponse {
//   error: {
//     details?: {
//       errors: {
//         message: string;
//       }[];
//     };
//     message?: string;
//   };
// }
export interface IErrorResponse {
  error?: {
    message?: string;
    [key: string]: any; // لو فيه مفاتيح إضافية
  };
  message?: string; // أحياناً السيرفر يرسل الرسالة مباشرة هنا
  [key: string]: any; // احتياط لأي خصائص إضافية
}
export type FormInputLogin = "userEmail" | "userPassword";
interface ILogin {
  name: FormInputLogin;
  placeholder: string;
  type: string;
  label: string;
}
export const LoginForm: ILogin[] = [
  {
    name: "userEmail",
    type: "email",
    placeholder: "name@example.com",
    label: "Your email",
  },
  {
    name: "userPassword",
    type: "password",
    placeholder: "********",
    label: "Password",
  },
];

export type FormInputRe = "userEmail";

export interface IReset {
  name: FormInputRe;
  placeholder: string;
  type: string;
  label: string;
}

export const ResetPassword: IReset = {
  name: "userEmail",
  type: "email",
  placeholder: "name@example.com",
  label: "Your email",
};

export type FormInputReset = "password" | "confirmPassword";

export interface IResetPas {
  name: FormInputReset;
  placeholder: string;
  type: string;
  label: string;
}

export const ResetinputPassword: IResetPas[] = [
  {
    name: "password",
    type: "password",
    placeholder: "********",
    label: "Password",
  },
  {
    name: "confirmPassword",
    type: "password",
    placeholder: "********",
    label: "Confirm Password",
  },
];
export type Inputs = {
  nameSession: string;
  descriptionSession: string;
  category: string;
  technologies: string[];
  fields: string[];
  privateSession: boolean;
};
export type FormInput = "nameSession" | "descriptionSession";

export interface Iinpform {
  name: FormInput;
  placeholder: string;
  type: string;
  label: string;
}
