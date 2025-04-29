import {
  Inputs,
  Textarea,
  SelectField,
  MultiSelectField,
  Button,
  useAuthQuery,
  CookiesService,
} from "../imports.ts";
import { inputData } from "../data/data.ts";
import { FormProvider, SubmitHandler, useForm } from "react-hook-form";
import {
  ISessionForm,
  Category,
  IErrorResponse,
  ISession,
} from "../interfaces.ts";
import axiosInstance from "../config/axios.config.ts";
import toast from "react-hot-toast";
import { AxiosError } from "axios";
interface IProps {
  session: ISession;
  closeModal: () => void;
}
const EditSessionForm = ({ session, closeModal }: IProps) => {
  /*______SelectData______*/
  const Token = CookiesService.get("UserToken");
  const { data: CategoryData } = useAuthQuery({
    queryKey: ["CategoryData"],
    url: "/tech-skills/categories",
    config: {
      headers: {
        Authorization: `Bearer ${Token}`,
      },
    },
  });
  const { data: fieldsData } = useAuthQuery({
    queryKey: ["fieldsData"],
    url: "/tech-skills/fields",
    config: {
      headers: {
        Authorization: `Bearer ${Token}`,
      },
    },
  });
  const fieldName = fieldsData?.map(
    (tech: { fieldName: string }) => tech.fieldName
  );
  const { data: technologiesData } = useAuthQuery({
    queryKey: ["technologiesData"],
    url: "/tech-skills/technologies",
    config: {
      headers: {
        Authorization: `Bearer ${Token}`,
      },
    },
  });
  const technologyNames = technologiesData?.map(
    (tech: { technologyName: string }) => tech.technologyName
  );
  const methods = useForm<ISessionForm>({
    defaultValues: {
      sessionName: session.sessionName,
      sessionDescription: session.sessionDescription,
      technologies: session.technologies,
      category: session.category,
    },
  });
  const onSubmit: SubmitHandler<ISessionForm> = async (data) => {
    console.log(data);

    try {
      await axiosInstance.put(`/sessions/${session.id}`, data, {
        headers: {
          Authorization: `Bearer ${Token}`,
        },
      });
      toast.success("Edit successful !", {
        position: "top-center",
        duration: 1000,
      });
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
          <label htmlFor={inputData.label}>{inputData.label}</label>
          <Inputs
            id={inputData.label}
            type="text"
            placeholder={inputData.placeholder}
            {...methods.register(inputData.name, {
              required: true,
              min: 8,
              max: 20,
            })}
          />
        </div>

        <div>
          <label htmlFor="descriptionSession">Project Description</label>
          <Textarea
            id="Project Description"
            placeholder="Project Description"
            {...methods.register("sessionDescription", {
              required: true,
              min: 100,
              max: 250,
            })}
          />
        </div>

        <div className="flex flex-row space-x-6">
          {["true", "false"].map((val) => (
            <label key={val}>
              <input
                type="radio"
                value={val}
                {...methods.register("privateSession", {
                  required: "Please select an option",
                })}
              />
              {val === "true" ? "Private" : "Public"} Session
            </label>
          ))}
        </div>

        {fieldName?.length > 0 && (
          <MultiSelectField<string>
            label="Fields"
            name="fields"
            options={fieldName}
            getLabel={(item) => item}
          />
        )}
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
        <div className="flex mt-4 space-x-2">
          <Button
            className=" hover:bg-green-400 bg-green-600 font-medium"
            width="w-full"
            type="submit"
          >
            Edit Session
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

export default EditSessionForm;
