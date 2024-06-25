import {useCallback, useEffect} from "react";


interface useShortCutProps {
  save?: () => void;
  pass?: () => void;
}

export const useShortCut = ({save, pass}: useShortCutProps) => {
  const handleKeyPress = useCallback((event: KeyboardEvent) => {
    if (save !== undefined && event.metaKey && event.key.toLowerCase() === 's') {
      event.preventDefault();

      save();
    }

    if(pass !== undefined && event.metaKey && event.key.toLowerCase() === 'p') {
      event.preventDefault();
      pass();
    }
  }, [save, pass]);

  useEffect(() => {
    window.addEventListener('keydown', handleKeyPress);
    return () => {
      window.removeEventListener('keydown', handleKeyPress);
    };
  }, [handleKeyPress]);
}
