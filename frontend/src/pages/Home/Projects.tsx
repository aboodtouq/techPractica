import { useState } from "react";
import { Category, ISessionForm } from "../..//interfaces";
import { FormProvider, SubmitHandler, useForm } from "react-hook-form";
import {
  Inputs,
  Button,
  CookiesService,
  Modal,
  MultiSelectField,
  useAuthQuery,
  SelectField,
  Textarea,
} from "../../imports.ts";
import { inputData } from "../../data/data.ts";
interface IProps {}
const Projects = ({}: IProps) => {
  /*______STATE______*/
  const [isOpen, setIsOpen] = useState(false);
  const closeModal = () => setIsOpen(false);
  const openModal = () => setIsOpen(true);
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
  const { data: technologiesData } = useAuthQuery({
    queryKey: ["technologiesData"],
    url: "/tech-skills/technologies",
    config: {
      headers: {
        Authorization: `Bearer ${Token}`,
      },
    },
  });
  console.log(fieldsData);
  /*______SUBMIT______*/
  const methods = useForm<ISessionForm>({});
  const onSubmit: SubmitHandler<ISessionForm> = (data) => {
    console.log(data);
  };

  return (
    <>
      <main className="container">
        <Button
          className=" bg-green-400 hover:bg-green-600 m-10 px-10 font-medium  "
          onClick={openModal}
          width=" w-fit"
        >
          Add Session
        </Button>
      </main>
      <Modal isOpen={isOpen} closeModal={closeModal} title="ADD A NEW SESSION">
        <FormProvider {...methods}>
          <form onSubmit={methods.handleSubmit(onSubmit)} className="space-y-4">
            <div>
              {" "}
              <label htmlFor={inputData.label}> {inputData.label}</label>
              <Inputs
                id={inputData.label}
                type="text"
                placeholder={inputData.placeholder}
                {...methods.register(inputData.name)}
              />
            </div>
            <div>
              {" "}
              <label htmlFor={inputData.label}> {inputData.label}</label>
              <Textarea
                id="Project Description"
                placeholder="Project Description"
                {...methods.register("descriptionSession")}
              />
            </div>
            <div className="flex flex-row space-x-6">
              <label>
                <input
                  type="radio"
                  value="true"
                  {...methods.register("privateSession", {
                    required: "Please select an option",
                  })}
                />
                {""} Private Session
              </label>

              <label>
                <input
                  type="radio"
                  value="false"
                  {...methods.register("privateSession", {
                    required: "Please select an option",
                  })}
                />
                {""} Public Session
              </label>
            </div>
            <SelectField<Category>
              label="Category"
              name="category"
              options={CategoryData}
              getLabel={(item) => item.categoryName}
            />
            <MultiSelectField<string>
              label="Fields"
              name="fields"
              options={fieldsData}
              getLabel={(item) => item}
            />
            <MultiSelectField<{ technologyName: string; categories: any[] }>
              label="Technologies"
              name="technologies"
              options={technologiesData}
              getLabel={(item) => item.technologyName}
            />
            <div className="flex mt-4  space-x-2">
              {" "}
              <Button
                className="block bg-green-400 hover:bg-green-600  font-medium"
                width="w-full"
                type="submit"
              >
                Create Session
              </Button>
              <Button
                className="block bg-red-400 hover:bg-red-600  font-medium"
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
      </Modal>
    </>
  );
};
export default Projects;
