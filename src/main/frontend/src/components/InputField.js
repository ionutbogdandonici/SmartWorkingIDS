import React from "react";
import { Field, ErrorMessage } from "formik";

function InputField({ label, placeholder, name, type, attributes, autoComplete, disabled }) {
    return (
        <div className={"my-2 " + attributes}>
            {!label ? null : (
                <label htmlFor={name} className="block text-sm font-medium text-gray-700 mb-1">
                    {label}
                </label>
            )}
            <Field disabled={disabled} autoComplete={autoComplete || "off"} className="px-3 py-2 focus:outline-none foucs:ring-2 focus:ring-green-500 shadow-sm block w-full border border-gray-300 rounded-md placeholder:text-gray-400 text-sm" id={name} name={name} type={type} placeholder={placeholder} />
            <ErrorMessage name={name} component="div" className="text-red-600 font-semibold text-sm mt-2" />
        </div>
    );
}

export default InputField;
