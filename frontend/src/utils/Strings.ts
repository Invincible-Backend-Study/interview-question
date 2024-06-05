type Input = string | null | undefined;


export class Strings {
  public static isString(input: Input) {
    return !(input === null || input === undefined);
  }

  public static toString(input: Input) {
    if (Strings.isString(input)) return <string>input;
    return "";
  }

  public static trim(input: Input) {
    return Strings.toString(input).trim();
  }

  public static isBlank(input: Input) {
    return Strings.trim(input).length === 0;
  }

  public static isNotBlank(input: Input) {
    return !Strings.isBlank(input);
  }


}
