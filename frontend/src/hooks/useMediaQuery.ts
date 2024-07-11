import {useEffect, useMemo, useRef, useState} from "react";

const MOBILE_MEDIA_QUERY = '(max-width: 780px)';

export const useMediaQuery = () => {
  const [matches, setMatches] = useState(() => window.matchMedia(MOBILE_MEDIA_QUERY).matches);
  const matchMediaRef = useRef<MediaQueryList | null>(null);

  useEffect(() => {
    matchMediaRef.current = window.matchMedia(MOBILE_MEDIA_QUERY);
    function handleChange() {
      setMatches(window.matchMedia(MOBILE_MEDIA_QUERY).matches);
    }
    matchMediaRef.current.addEventListener('change', handleChange);

    return () => {
      matchMediaRef.current?.removeEventListener('change', handleChange);
    };
  }, []);


  const modalStatement = useMemo<"bottom-center" | "top">(() => matches ? "bottom-center" : "top", [])



  return { isMobile: matches , modalPlacement: modalStatement};
};


