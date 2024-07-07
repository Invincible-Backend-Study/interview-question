import {useEffect, useState} from "react";


export const useFileReader = () => {

  const [file, setFile] = useState<File | undefined>();
  const reader = new FileReader();
  const [lines, setLines]= useState<string[]>([]);

  const handleChange = (file: File) => {
    setFile(file);
  };

  useEffect(() => {


    reader.onload = () => {
      if(reader.result === null){
        setLines([]);
        return ;
      }
      const text = reader.result.toString();

      setLines(text.split("\n")
        .map(s => s.trim())
        .filter(s => s.length !== 0));
    }

    if(file !== undefined){
      reader.readAsText(file);
    }

    return () => {
      reader.onload = null;
    }
  }, [file])


  return {lines, handleChange}
}
