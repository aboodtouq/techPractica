import { InputHTMLAttributes, forwardRef } from "react";

interface IProps extends InputHTMLAttributes<HTMLInputElement> {
  errorStyle?: boolean;
}

const Inputs = forwardRef<HTMLInputElement, IProps>(
  ({ errorStyle, ...rest }, ref) => {
    return (
      <input
        ref={ref}
        className={`border-[1px] border-gray-300 shadow-lg ${
          errorStyle
            ? " focus:ring-red-500  focus:border-red-500"
            : " focus:ring-black focus:border-black"
        }  focus:outline-none focus:ring-1 rounded-lg px-3 py-3 text-md w-full bg-transparent `}
        {...rest}
      />
    );
  }
);

export default Inputs;
