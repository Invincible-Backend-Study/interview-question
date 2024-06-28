import {nextui} from "@nextui-org/react"

/** @type {import('tailwindcss').Config} */
module.exports = {
    content: [
        "./index.html",
        "./src/**/*.{html,js}",
        "./src/**/*.{html,js,ts,tsx}",
        "./node_modules/@nextui-org/theme/dist/**/*.{js,ts,jsx,tsx}",
    ],
    theme: {
        extend: {

        },
    },
    darkMode: "class",
    plugins: [nextui({
        themes: {
            dark: {
                colors: {
                    background: '#11181C'
                },
            }
        },

    })]
}
