import {Link} from "react-router-dom";

import image from "@/assets/error.jpg"

const NotFoundPage = () => {
  return <div className="flex flex-col justify-center items-center h-screen ">
      <div className="text-center">
        <img
          src={image}
          className="w-64 mb-8"
        />
        <h1 className="text-6xl font-bold text-pink-500 mb-4">404</h1>
        <p className="text-xl text-gray-700 mb-6">
          오 이상한 페이지로 들어오셨군요~
        </p>
        <Link
          to="/"
          className="bg-pink-500 text-white py-2 px-4 rounded-full text-lg transition duration-300 hover:bg-pink-600"
        >
          홈으로 가시죠
        </Link>
      </div>
    </div>
}

export default NotFoundPage;
