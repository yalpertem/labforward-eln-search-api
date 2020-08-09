import axios from "axios";
import * as Const from "../util/Constants";

export default axios.create({
  baseURL: Const.API_BASE_URL,
  responseType: "json",
});
