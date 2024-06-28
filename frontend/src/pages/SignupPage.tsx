import SignupForm from "@/components/SignupForm/SignupForm";
import {useSearchParams} from "react-router-dom";
import {PATH} from "@/constants/path";

const SignupPage = () => {
  const [urlParams] = useSearchParams();
  const providerId = urlParams.get("code");

  if(providerId === null || providerId === undefined) {
    location.assign(PATH.AUTH);
    return;
  }



  return <SignupForm providerId={providerId}/>
}

export default SignupPage;
