import { Controller, get, useFormContext } from "react-hook-form";
import { Editor } from "@tinymce/tinymce-react";
import ErrorMsg from "./ErrorMsg";

const TinyMCEWithForm = () => {
  const {
    control,
    formState: { errors },
  } = useFormContext();
  const errorMessage = get(errors, "descriptionSession")?.message as
    | string
    | undefined;

  return (
    <div>
      <Controller
        name="descriptionSession"
        control={control}
        defaultValue=""
        render={({ field }) => (
          <div>
            <Editor
              apiKey={import.meta.env.VITE_TINYMCE_API_KEY}
              value={field.value}
              onEditorChange={(content: string) => field.onChange(content)}
              init={{
                height: 300,
                menubar: false,
                plugins: ["lists"],
                toolbar:
                  "undo redo | styleselect | bold italic underline | bullist numlist",
              }}
            />

            {errors && <ErrorMsg Msg={errorMessage} />}
          </div>
        )}
      />
    </div>
  );
};

export default TinyMCEWithForm;
