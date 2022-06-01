import React from "react";

const defaultStyle = "border px-4 py-2 shadow-sm text-base font-medium rounded-md";

const primaryStyle = "inline-flex items-center px-4 py-2 border border-transparent text-sm font-medium rounded-md shadow-sm text-white bg-green-600 hover:bg-green-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-green-500";

const getStyle = (style) => {
    switch (style) {
        case "primary":
            return defaultStyle+' '+primaryStyle;
        default:
            return primaryStyle;
    }
};

function Button({type, style, text}) {
    return <button type={type} className={getStyle(style)}>{text}</button>;
}

export default Button;
