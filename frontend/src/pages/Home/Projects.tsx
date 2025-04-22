import { useState } from "react";
import Button from "../../components/ui/Buttom";
import Modal from "../../components/ui/Modal";
import Inputs from "../../components/ui/Input";
import { FormProvider, SubmitHandler, useForm } from "react-hook-form";
import Textarea from "../../components/ui/Textarea";
import { Iinpform, Inputs as InputInterf } from "../../interfaces";
import SelectField from "../../components/ui/Select";
import MultiSelectField from "../../components/ui/muiltselect";
interface IProps {}
const Projects = ({}: IProps) => {
  /*______STATE______*/
  const [isOpen, setIsOpen] = useState(false);
  const closeModal = () => setIsOpen(false);
  const openModal = () => setIsOpen(true);

  /*______SUBMIT______*/
  const methods = useForm<InputInterf>({});
  const onSubmit: SubmitHandler<InputInterf> = (data) => {
    console.log(data);
  };

  const inputData: Iinpform = {
    name: "nameSession",
    type: "text",
    placeholder: "Project Name",
    label: "Project Name",
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
            <SelectField
              label="Category"
              name="category"
              options={["web", "mobile"]}
              getLabel={(category) => category}
            />
            <MultiSelectField
              label="Fields"
              name="fields"
              options={["BackEnd", "FrontEnd"]}
            />
            <MultiSelectField
              label="Technologies"
              name="technologies"
              options={["java", "node.js"]}
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
