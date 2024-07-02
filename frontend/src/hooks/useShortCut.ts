import {useCallback, useEffect} from "react";


interface useShortCutProps {
  save: () => void;
  pass: () => void;
  quit: () => void;
  isBlocking: boolean;
}

export const useShortCut = ({save, pass, isBlocking, quit}: useShortCutProps) => {

  const handleKeyPress = useCallback((event: KeyboardEvent) => {
    if(event.metaKey && event.key.toLowerCase() === 'q') {
      event.stopPropagation();
      quit();
    }
    if(isBlocking){
      return ;
    }
    if(event.metaKey && event.key.toLowerCase() === 's') {
      event.preventDefault();
      save();
    }
    if(event.metaKey && event.key.toLowerCase() === 'p') {
      console.log('call pass')
      event.preventDefault();
      pass();
    }
  }, [save, pass, isBlocking]); // 1초 동안 같은 이벤트 무시


  useEffect(() => {
    window.addEventListener('keydown', handleKeyPress);
    return () => {
      window.removeEventListener('keydown', handleKeyPress);
    };
  }, [handleKeyPress]);
}
