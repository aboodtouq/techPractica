import * as yup from "yup";
export const registerSchema = yup
  .object({
    firstName: yup
      .string()
      .required("First Name is required")
      .min(3, "Must be at least 3 characters")
      .max(18, "Must be 20 characters or less"),
    lastName: yup
      .string()
      .required("Last Name is required")
      .min(3, "Must be at least 3 characters")
      .max(18, "Must be 20 characters or less"),
    name: yup
      .string()
      .required("Username is required")
      .min(3, "Must be at least 3 characters")
      .max(20, "Must be 20 characters or less")
      .matches(
        /^[a-zA-Z0-9_.]+$/,
        "Only letters, numbers, underscores, and dots allowed"
      ),
    userEmail: yup
      .string()
      .required("Email is required")
      .max(320, "Must be 320 characters or less")
      .matches(/^[^@ ]+@[^@ ]+\.[^@ .]{2,}$/, "Not a valid email address."),
    userPassword: yup
      .string()
      .required("Password is required")
      .min(8, "Password should be at least 8 charachters.")
      .max(100, "Must be 100 characters or less"),
  })
  .required();
export const loginSchema = yup
  .object({
    userEmail: yup
      .string()
      .required("Email is required")
      .max(320, "Must be 320 characters or less")
      .matches(/^[^@ ]+@[^@ ]+\.[^@ .]{2,}$/, "Not a valid email address."),
    userPassword: yup
      .string()
      .required("Password is required")
      .min(8, "Password should be at least 8 charachters.")
      .max(100, "Must be 100 characters or less"),
  })
  .required();
export const ResetSchema = yup
  .object({
    userEmail: yup
      .string()
      .required("Email is required")
      .max(320, "Must be 320 characters or less")
      .matches(/^[^@ ]+@[^@ ]+\.[^@ .]{2,}$/, "Not a valid email address."),
  })
  .required();


export const ResetPassSchema = yup
  .object({
    password: yup
      .string()
      .required("Password is required")
      .min(8, "Password should be at least 8 charachters.")
      .max(100, "Must be 100 characters or less"),
    confirmPassword: yup
      .string()
      .oneOf([yup.ref("password")], "Passwords must match")
      .required("Confirm Password is required"),
  })
  .required();
