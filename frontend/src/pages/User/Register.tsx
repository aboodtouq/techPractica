import { useState } from "react";
import Inputs from "../../components/ui/Input";
import { BiHide, BiShow } from "react-icons/bi";
import { SubmitHandler, useForm } from "react-hook-form";
import { yupResolver } from "@hookform/resolvers/yup";
import { registerSchema } from "../../validation";
import { IErrorResponse, RegisterForm } from "../../interfaces";
import ErrorMsg from "../../components/ui/ErrorMsg";
import Button from "../../components/ui/Buttom";
import { Link, useNavigate } from "react-router-dom";
import axiosInstance from "../../config/axios.config";
import toast from "react-hot-toast";
import { AxiosError } from "axios";

const Register = () => {
  const navigate = useNavigate();

  type IFormInput = {
    firstName: string;
    lastName: string;
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
  const onSubmit: SubmitHandler<IFormInput> = async (data) => {
    try {
      const response = await axiosInstance.post("/registration", data);

      setTimeout(() => {
        navigate("/User");
      });
    } catch (error) {
      const ErrorObj = error as AxiosError<IErrorResponse>;
      toast.error(`${ErrorObj.response?.data.message}`, {
        position: "top-center",
        duration: 2000,
      });
    }
  };
  /* _______RENDER_______ */
  const [showPassword, setShowPassword] = useState(false);
  const RenderRegisterForm = RegisterForm.map(
    ({ label, name, placeholder, type }) => {
      const isPasswordField = type === "password";
      return (
        <div key={name}>
          <label htmlFor={label}>{label}</label>
          <div className="relative">
            <Inputs
              id={label}
              type={
                isPasswordField ? (showPassword ? "text" : "password") : type
              }
              placeholder={placeholder}
              {...register(name)}
            />
            {isPasswordField && (
              <button
                type="button"
                onClick={() => setShowPassword(!showPassword)}
                className="absolute right-3 top-1/2 -translate-y-1/2"
              >
                {showPassword ? (
                  <BiHide className="w-5 h-5 text-gray-500" />
                ) : (
                  <BiShow className="w-5 h-5 text-gray-500" />
                )}
              </button>
            )}
          </div>

          {errors[name] && <ErrorMsg Msg={errors[name]?.message} />}
        </div>
      );
    }
  );

  return (
    <>
      <h2 className="text-2xl font-semibold mb-6 text-center">Register</h2>

      <form className="space-y-4 " onSubmit={handleSubmit(onSubmit)}>
        {RenderRegisterForm}
        <Button
          type="submit"
          disabled={isSubmitting}
          className="align-middle  bg-green-400  focus:bg-green-600 "
        >
          Create your account
        </Button>
        <p className="text-center text-sm text-gray-500">
          Already a member?{" "}
          <Link to="/User" className="text-black">
            {" "}
            Sign in
          </Link>
        </p>
      </form>
    </>
  );
};

export default Register;
