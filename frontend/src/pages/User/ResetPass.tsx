import { useState } from "react";
import { useForm, SubmitHandler } from "react-hook-form";
import { yupResolver } from "@hookform/resolvers/yup";
import { ResetSchema, ResetPassSchema } from "../../validation";
import {
  IErrorResponse,
  ResetinputPassword,
  ResetPassword,
} from "../../interfaces";
import Inputs from "../../components/ui/Input";
import ErrorMsg from "../../components/ui/ErrorMsg";
import Button from "../../components/ui/Buttom";
import { useNavigate } from "react-router-dom";
import { BiHide, BiShow } from "react-icons/bi";
import { AxiosError } from "axios";
import axiosInstance from "../../config/axios.config";
import toast from "react-hot-toast";
import CookiesService from "../../service.ts";

const ResetPass = () => {
  const navigate = useNavigate();
  const [step, setStep] = useState<1 | 2 | 3>(1);
  const [userData, setUserData] = useState<{
    otpId?: number;
    otp?: string;
    userEmail?: string;
  }>({});

  // Step 1: Email
  type EmailForm = { userEmail: string };
  const {
    register: registerEmail,
    handleSubmit: handleEmailSubmit,
    formState: { errors: emailErrors, isSubmitting: isSubmittingEmail },
  } = useForm<EmailForm>({ resolver: yupResolver(ResetSchema) });

  const onSubmitEmail: SubmitHandler<EmailForm> = async (data) => {
    try {
      const response = await axiosInstance.post("/send-reset-password", data);
      if (response.status === 200) {
        setUserData({
          userEmail: data.userEmail,
          otpId: response.data.otpId,
        });
        setTimeout(() => setStep(2));
      }
    } catch (error) {
      const err = error as AxiosError<IErrorResponse>;
      toast.error(
        err.response?.data?.error?.message || "Something went wrong!",
        {
          position: "top-center",
          duration: 2000,
        }
      );
    }
  };

  // Step 2: OTP
  type OtpForm = {
    otpId: number;
    otp: string;
    userEmail: string;
  };

  const {
    register: registerOtp,
    handleSubmit: handleOtpSubmit,
    formState: { errors: otpErrors, isSubmitting: isSubmittingOtp },
  } = useForm<OtpForm>({});

  const onSubmitOtp: SubmitHandler<OtpForm> = async (data) => {
    try {
      const response = await axiosInstance.post("/submit-OTP", data);
      if (response.status === 200) {
        CookiesService.set("jwt", response.data);

        setTimeout(() => setStep(3));
      }
    } catch (error) {
      const errorObj = error as AxiosError<IErrorResponse>;
      toast.error(`${errorObj.response?.data.message}`, {
        position: "top-center",
        duration: 2000,
      });
    }
  };
  // Step 3: New Password
  type PasswordForm = { password: string; confirmPassword: string };
  const {
    register: registerPass,
    handleSubmit: handlePassSubmit,
    formState: { errors: passErrors, isSubmitting: isSubmittingPass },
  } = useForm<PasswordForm>({ resolver: yupResolver(ResetPassSchema) });
  const Token = CookiesService.get("jwt");
  const onSubmitPassword: SubmitHandler<PasswordForm> = async (data) => {
    try {
      const response = await axiosInstance.post("/submit-new-password", data, {
        headers: {
          Authorization: `Bearer ${Token}`,
        },
      });
      if (response.status === 200) {
        CookiesService.remove("jwt");
        setTimeout(() => navigate("/User"), 1000);
      }
    } catch (error) {
      const errorObj = error as AxiosError<IErrorResponse>;

      toast.error(`${errorObj.response?.data.message}`, {
        position: "top-center",
        duration: 2000,
      });
    }
  };

  const [showPassword, setShowPassword] = useState(false);
  const RenderRegisterForm = ResetinputPassword.map(
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
              {...registerPass(name)}
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
          {passErrors[name] && <ErrorMsg Msg={passErrors[name]?.message} />}
        </div>
      );
    }
  );

  return (
    <div className="mt-10">
      <h2 className="text-2xl font-semibold mb-6 text-center">
        Reset your password
      </h2>

      {step === 1 && (
        <form onSubmit={handleEmailSubmit(onSubmitEmail)}>
          <label>{ResetPassword.label}</label>
          <Inputs
            id={ResetPassword.label}
            type={ResetPassword.type}
            placeholder={ResetPassword.placeholder}
            {...registerEmail(ResetPassword.name)}
          />
          {emailErrors[ResetPassword.name] && (
            <ErrorMsg Msg={emailErrors[ResetPassword.name]?.message} />
          )}
          <Button
            type="submit"
            disabled={isSubmittingEmail}
            className="bg-green-400 hover:bg-green-600 mt-6"
          >
            Send OTP
          </Button>
        </form>
      )}

      {step === 2 && (
        <form onSubmit={handleOtpSubmit(onSubmitOtp)}>
          <label>Enter OTP Code</label>
          <input
            type="hidden"
            value={userData.otpId}
            {...registerOtp("otpId")}
          />

          <Inputs
            type="text"
            placeholder="OTP"
            {...registerOtp("otp", {
              required: "OTP is required",
              pattern: {
                value: /^\d{6}$/,
                message: "OTP must be exactly 6 digits",
              },
            })}
          />
          {otpErrors.otp && <ErrorMsg Msg={otpErrors.otp.message} />}

          <input
            type="hidden"
            value={userData.userEmail}
            {...registerOtp("userEmail")}
          />

          <Button
            type="submit"
            disabled={isSubmittingOtp}
            className="bg-green-400 hover:bg-green-600 mt-6"
          >
            Verify OTP
          </Button>
        </form>
      )}

      {step === 3 && (
        <form onSubmit={handlePassSubmit(onSubmitPassword)}>
          {RenderRegisterForm}
          <Button
            type="submit"
            disabled={isSubmittingPass}
            className="bg-green-400 hover:bg-green-600 mt-6"
          >
            Reset Password
          </Button>
        </form>
      )}

      <Button
        type="button"
        onClick={() => navigate("/User")}
        className="bg-green-400 hover:bg-green-600 mt-2"
      >
        Back to Login
      </Button>
    </div>
  );
};

export default ResetPass;
