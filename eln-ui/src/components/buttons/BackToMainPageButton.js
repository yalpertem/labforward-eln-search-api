import React from "react";
import Fab from "@material-ui/core/Fab";
import ArrowBackIcon from "@material-ui/icons/ArrowBack";
import { makeStyles } from "@material-ui/core/styles";

const useStyles = makeStyles((theme) => ({
  entryButtonStyle: {
    position: "fixed",
    bottom: theme.spacing(2),
    right: theme.spacing(4),
  },
  extendedIcon: {
    marginRight: theme.spacing(1),
  },
}));

const BackToMainPageButton = (props) => {
  const classes = useStyles();

  return (
    <>
      <Fab
        onClick={props.clicked}
        variant="extended"
        color="secondary"
        aria-label="addAll"
        className={classes.entryButtonStyle}
      >
        <ArrowBackIcon className={classes.extendedIcon} /> Back
      </Fab>
    </>
  );
};

export default BackToMainPageButton;
