import * as Constants from "./Constants";

export const excerpt = (text) => {
  return text.length > Constants.MAX_LEN_CONTENT
    ? text.substr(0, Constants.MAX_LEN_CONTENT) + "..."
    : text;
};
