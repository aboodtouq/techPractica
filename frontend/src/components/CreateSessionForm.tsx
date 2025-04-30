import {
  Inputs,
  Textarea,
  SelectField,
  MultiSelectField,
  Button,
  ErrorMsg,
} from "../imports.ts";
import { FormProvider, SubmitHandler, useForm } from "react-hook-form";
import { Category, IErrorResponse } from "../interfaces.ts";
import axiosInstance from "../config/axios.config.ts";
import toast from "react-hot-toast";
import { AxiosError } from "axios";
import { token, useCategories, useFields, useTechnologies } from "../api.ts";
import { yupResolver } from "@hookform/resolvers/yup";
import { sessionSchema } from "../validation/index.ts";
import { InferType } from "yup";
interface IProps {
  closeModal: () => void;
}

const CreateSessionForm = ({ closeModal }: IProps) => {
  /*______SelectData______*/
  const { data: CategoryData } = useCategories();
  const { data: fieldsData } = useFields();
  const { data: technologiesData } = useTechnologies();
  const fieldNames = fieldsData?.map(
    (tech: { fieldName: string }) => tech.fieldName
  );
  const technologyNames = technologiesData?.map(
    (tech: { technologyName: string }) => tech.technologyName
  );

  /*______onSubmit______*/
  type CreateSession = InferType<typeof sessionSchema>;

  const methods = useForm<CreateSession>({
    resolver: yupResolver(sessionSchema),
  });
  const onSubmit: SubmitHandler<CreateSession> = async (data) => {
    const formattedData = {
      ...data,
      privateSession: data.privateSession === "Private Session",
    };

    try {
      await axiosInstance.post("/sessions/", formattedData, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      toast.success("Create successful!", {
        position: "top-center",
        duration: 1000,
      });
      setTimeout(() => {
        closeModal();
        window.location.href = window.location.href;
      }, 500);
    } catch (error) {
      const ErrorObj = error as AxiosError<IErrorResponse>;
      toast.error(`${ErrorObj.response?.data.message}`, {
        position: "top-center",
        duration: 2000,
      });
    }
  };
  return (
    <FormProvider {...methods}>
      <form onSubmit={methods.handleSubmit(onSubmit)} className="space-y-4">
        <div>
          <label htmlFor="SessionName">Session Name</label>
          <Inputs
            id="SessionName"
            type="text"
            placeholder="Session Name"
            {...methods.register("nameSession")}
          />
          {methods.formState.errors && (
            <ErrorMsg Msg={methods.formState.errors.nameSession?.message} />
          )}
        </div>

        <div>
          <label htmlFor="descriptionSession">Project Description</label>
          <Textarea
            id="Project Description"
            placeholder="Project Description"
            {...methods.register("descriptionSession")}
          />
          {methods.formState.errors && (
            <ErrorMsg
              Msg={methods.formState.errors.descriptionSession?.message}
            />
          )}
        </div>

        {CategoryData?.length > 0 && (
          <SelectField<Category>
            label="Category"
            name="category"
            options={CategoryData}
            getLabel={(item) => item.categoryName}
          />
        )}

        {technologyNames?.length > 0 && (
          <MultiSelectField<string>
            label="Technologies"
            name="technologies"
            options={technologyNames}
            getLabel={(item) => item}
          />
        )}
        {fieldNames?.length > 0 && (
          <MultiSelectField<string>
            label="Fields"
            name="fields"
            options={fieldNames}
            getLabel={(item) => item}
          />
        )}

        <SelectField<string>
          label="Sesseion State"
          name="privateSession"
          options={["Public Session", "Private Session"]}
          getLabel={(item) => item}
        />
        <div className="flex mt-4 space-x-2">
          <Button
            className=" hover:bg-green-400 bg-green-600 font-medium"
            width="w-full"
            type="submit"
          >
            Create Session
          </Button>
          <Button
            className=" hover:bg-red-400 bg-red-600 font-medium"
            width="w-full"
            type="button"
            onClick={() => {
              closeModal();
              methods.reset();
            }}
          >
            Cancel
          </Button>
        </div>
      </form>
    </FormProvider>
  );
};

export default CreateSessionForm;
