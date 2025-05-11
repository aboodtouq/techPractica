import { useState } from "react";
import Inputs from "../../components/ui/Input";
import { BiHide, BiShow } from "react-icons/bi";
import { SubmitHandler, useForm } from "react-hook-form";
import { yupResolver } from "@hookform/resolvers/yup";
import { registerSchema } from "../../validation";
import { IErrorResponse, IRegister, RegisterForm } from "../../interfaces";
import ErrorMsg from "../../components/ui/ErrorMsg";
import Button from "../../components/ui/Buttom";
import { Link, useNavigate } from "react-router-dom";
import axiosInstance from "../../config/axios.config";
import toast from "react-hot-toast";
import { AxiosError } from "axios";

const Register = () => {
  document.title = "TechPractica | Register";

  const navigate = useNavigate();

  type IFormInput = {
    firstName?: string;
    lastName?: string;
    name: string;
    userEmail: string;
    userPassword: string;
  };

  const {
    register,
    handleSubmit,
    formState: { errors, isSubmitting },
  } = useForm<IFormInput>({
    resolver: yupResolver(registerSchema),
  });

  const [showPassword, setShowPassword] = useState(false);

  const onSubmit: SubmitHandler<IFormInput> = async (data) => {
    try {
      await axiosInstance.post("/authenticated/registration", data);
      toast.success("Registration successful!", {
        position: "top-center",
        duration: 2000,
      });
      setTimeout(() => navigate("/User"), 1500);
    } catch (error) {
      const ErrorObj = error as AxiosError<IErrorResponse>;
      toast.error(`${ErrorObj.response?.data.message}`, {
        position: "top-center",
        duration: 2000,
      });
    }
  };

  const renderInputField = ({ label, name, placeholder, type }: IRegister) => {
    const isPasswordField = type === "password";
    return (
      <div className="mb-4">
        <label
          hidden={name === "lastName" || name === "firstName"}
          htmlFor={label}
          className="block text-sm font-medium text-gray-700 mb-1"
        >
          {label}
        </label>
        <div className="relative">
          <Inputs
            hidden={name === "lastName" || name === "firstName"}
            defaultValue={name === "lastName" || name === "firstName" ? "" : ""}
            id={label}
            type={isPasswordField ? (showPassword ? "text" : "password") : type}
            placeholder={placeholder}
            {...register(name)}
            className="w-full px-4 py-3 text-base border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-[#42D5AE] focus:border-transparent transition-colors duration-200"
          />
          {isPasswordField && (
            <button
              type="button"
              onClick={() => setShowPassword(!showPassword)}
              className="absolute right-3 top-1/2 -translate-y-1/2 text-gray-500 hover:text-[#42D5AE] transition-colors"
            >
              {showPassword ? (
                <BiHide className="w-5 h-5" />
              ) : (
                <BiShow className="w-5 h-5" />
              )}
            </button>
          )}
        </div>
        {errors[name] && <ErrorMsg Msg={errors[name]?.message} />}
      </div>
    );
  };

  return (
    <div className="max-w-md mx-auto bg-white p-8 rounded-xl shadow-md border border-gray-100">
      <div className="text-center mb-8">
        <h2 className="text-3xl font-bold text-[#022639]">Create Account</h2>
        <p className="text-gray-600 mt-2">Join our community today</p>
      </div>

      <form onSubmit={handleSubmit(onSubmit)} className="space-y-6">
        {RegisterForm.map(renderInputField)}

        <Button
          type="submit"
          disabled={isSubmitting}
          className="w-full py-3 px-4 bg-[#42D5AE] hover:bg-[#38b28d] text-white font-medium rounded-lg shadow-sm transition-colors duration-300 flex items-center justify-center"
        >
          {isSubmitting ? (
            <>
              <svg
                className="animate-spin -ml-1 mr-3 h-5 w-5 text-white"
                xmlns="http://www.w3.org/2000/svg"
                fill="none"
                viewBox="0 0 24 24"
              >
                <circle
                  className="opacity-25"
                  cx="12"
                  cy="12"
                  r="10"
                  stroke="currentColor"
                  strokeWidth="4"
                ></circle>
                <path
                  className="opacity-75"
                  fill="currentColor"
                  d="M4 12a8 8 0 018-8V0C5.373 0 0 5.373 0 12h4zm2 5.291A7.962 7.962 0 014 12H0c0 3.042 1.135 5.824 3 7.938l3-2.647z"
                ></path>
              </svg>
              Creating account...
            </>
          ) : (
            "Create Account"
          )}
        </Button>

        <div className="text-center text-sm text-gray-600">
          Already a member?{" "}
          <Link
            to="/User"
            className="font-medium text-[#42D5AE] hover:text-[#38b28d] transition-colors"
          >
            Sign in
          </Link>
        </div>
      </form>
    </div>
  );
};

export default Register;
