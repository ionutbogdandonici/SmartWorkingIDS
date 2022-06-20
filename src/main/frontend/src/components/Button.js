import React from "react";

const defaultStyle = "border px-4 py-2 shadow-sm text-base font-medium rounded-md";

const primaryStyle = "items-center border-transparent text-white bg-green-600 hover:bg-green-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-green-500";
const secondaryStyle = "items-center border-gray-300 text-gray-700 bg-white hover:bg-gray-50 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-green-500";

const getStyle = (decoration) => {
    switch (decoration) {
        case "primary":
            return defaultStyle + " " + primaryStyle;
        case "secondary":
            return defaultStyle + " " + secondaryStyle;
        default:
            return primaryStyle;
    }
};

function Button({ type, decoration, text, otherCSS, onClick }) {
    return (
        <button type={type} className={getStyle(decoration)+" "+otherCSS} onClick={onClick}>
            {text}
        </button>
    );
}

export default Button;
