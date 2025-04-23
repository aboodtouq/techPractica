import { useState } from "react";
import { Inputs, CookiesService, Button, ErrorMsg } from "../../imports.ts";
import { IErrorResponse, LoginForm } from "../../interfaces.ts";
import { SubmitHandler, useForm } from "react-hook-form";
import { yupResolver } from "@hookform/resolvers/yup";
import { loginSchema } from "../../validation/index.ts";
import { BiHide, BiShow } from "react-icons/bi";
import { Link, useNavigate } from "react-router-dom";
import axiosInstance from "../../config/axios.config.ts";
import toast from "react-hot-toast";
import { AxiosError } from "axios";

const Login = () => {
  const navigate = useNavigate();
  type IFormInput = {
    userEmail: string;
    userPassword: string;
  };
  const {
    register,
    handleSubmit,
    formState: { errors, isSubmitting },
  } = useForm<IFormInput>({
    resolver: yupResolver(loginSchema),
  });
  const onSubmit: SubmitHandler<IFormInput> = async (data) => {
    try {
      const response = await axiosInstance.post("/authenticated/login", data);
      if (response.status == 200) {
        toast.success(" Login successful !", {
          position: "top-center",
          duration: 1000,
        });
        CookiesService.set("UserToken", response.data);
      }
      setTimeout(() => {
        navigate("/");
      }, 1500);
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
  const RenderLoginForm = LoginForm.map(
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
      <h2 className="text-2xl font-semibold mb-6 text-center">Login</h2>
      <form className="space-y-4 pt-10" onSubmit={handleSubmit(onSubmit)}>
        {RenderLoginForm}
        <div className="flex justify-end text-sm">
          <Link to="/User/ResetPassword" className="text-black">
            Forgot password?
          </Link>
        </div>

        <Button
          className="align-middle  bg-green-400 hover:bg-green-600"
          disabled={isSubmitting}
        >
          Login
        </Button>

        <p className="text-center text-sm text-gray-500">
          New here?{" "}
          <Link to="/User/Register" className="text-black">
            Create an account
          </Link>
        </p>
      </form>
    </>
  );
};
export default Login;
