import { useState } from "react";
import { Button, CookiesService, ErrorMsg, Inputs } from "../../imports";
import { BiHide, BiShow } from "react-icons/bi";
import { SubmitHandler, useForm } from "react-hook-form";
import { yupResolver } from "@hookform/resolvers/yup";
import { loginSchema } from "../../validation";
import { Link, useNavigate } from "react-router-dom";
import axiosInstance from "../../config/axios.config";
import toast from "react-hot-toast";
import { AxiosError } from "axios";
import { LoginForm } from "../../interfaces";

const Login = () => {
  const navigate = useNavigate();
  const [showPassword, setShowPassword] = useState(false);
  const [isSubmitting, setIsSubmitting] = useState(false);

  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm({
    resolver: yupResolver(loginSchema),
  });
  type IFormInput = {
    userEmail: string;
    userPassword: string;
  };
  const onSubmit: SubmitHandler<IFormInput> = async (data) => {
    try {
      setIsSubmitting(true);
      const response = await axiosInstance.post("/authenticated/login", data);
      toast.success("Login successful!", { position: "top-center" });
      CookiesService.set("UserToken", response.data);

      navigate("/");
    } catch (error) {
      const err = error as AxiosError<{ message: string }>;
      toast.error(err.response?.data.message || "Login failed", {
        position: "top-center",
      });
    } finally {
      setIsSubmitting(false);
    }
  };

  const renderForm = LoginForm.map(({ label, name, placeholder, type }) => {
    const isPasswordField = type === "password";

    return (
      <div key={name} className="space-y-2">
        <label
          htmlFor={label}
          className="block text-sm font-medium text-gray-700"
        >
          {label}
        </label>
        <div className="relative">
          <Inputs
            id={label}
            type={
              type === "password" ? (showPassword ? "text" : "password") : type
            }
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
  });

  return (
    <div className="bg-white rounded-xl shadow-lg p-8 w-full">
      <div className="text-center mb-8">
        <h2 className="text-2xl font-bold text-[#022639]">Welcome Back</h2>
        <p className="text-gray-600 mt-2">Sign in to your account</p>
      </div>

      <form onSubmit={handleSubmit(onSubmit)} className="space-y-5">
        {renderForm}
        <div className="flex items-center justify-end">
          <Link
            to="/User/ResetPassword"
            className="text-sm font-medium text-[#42D5AE] hover:text-[#38b28d]"
          >
            Forgot password?
          </Link>
        </div>

        <Button
          type="submit"
          disabled={isSubmitting}
          className="w-full py-3 px-4 bg-[#42D5AE] hover:bg-[#38b28d] text-white font-medium rounded-lg shadow-sm transition-colors duration-300"
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
              Signing in...
            </>
          ) : (
            "Sign In"
          )}
        </Button>

        <div className="text-center text-sm text-gray-600">
          Don't have an account?{" "}
          <Link
            to="/User/Register"
            className="font-medium text-[#42D5AE] hover:text-[#38b28d]"
          >
            Sign up
          </Link>
        </div>
      </form>
    </div>
  );
};

export default Login;
