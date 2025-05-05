import { FormProvider, SubmitHandler, useForm } from "react-hook-form";
import { Button, MultiSelectField } from "../imports";
import TinyMCEWithForm from "./ui/RichTextEditor";

interface IProps {
  closeModal: () => void;
  f: string[] | undefined;
}
interface IREQ {
  BreefMsg: string;
  Fields: string[] | undefined;
}
const ApplySessionForm = ({ closeModal, f }: IProps) => {
  const methods = useForm<IREQ>();

  const onSubmit: SubmitHandler<IREQ> = async (data) => {
    console.log(data);
  };
  return (
    <>
      <FormProvider {...methods}>
        <form onSubmit={methods.handleSubmit(onSubmit)} className="space-y-4">
          <div>
            <label className="block text-sm font-medium text-gray-700">
              Introduction message
            </label>
            <TinyMCEWithForm name="BreefMsg" />
          </div>
          {f?.length ? (
            <MultiSelectField<string>
              label="Session Fields"
              name="Fields"
              options={f}
              getLabel={(item) => item}
            />
          ) : (
            ""
          )}

          <div className="flex mt-6 gap-4">
            <Button
              className="bg-[#42D5AE] hover:bg-[#38b28d] text-white font-medium transition-colors duration-200 cursor-pointer"
              type="submit"
              width="w-full"
            >
              Submit
            </Button>
            <Button
              className="bg-white border border-gray-300 !text-[#022639] hover:bg-gray-50 font-medium transition-colors duration-200 cursor-pointer"
              type="button"
              width="w-full"
              onClick={closeModal}
            >
              Cancel
            </Button>
          </div>
        </form>
      </FormProvider>
    </>
  );
};
export default ApplySessionForm;
