import React from "react";
import Warning from "../alert/Warning";
import Grid from "@material-ui/core/Grid";
import EntryExcerptCard from "./EntryExcerptCard";
import * as Const from "../../util/Constants";
import { CircularProgress } from "@material-ui/core";
import AddScreenButton from "../buttons/AddScreenButton";
import AddDummyEntryButton from "../buttons/AddDummyEntryButton";

const EntryList = (props) => {
  let view = "";
  switch (props.status) {
    case Const.LIST_STATUS_LOADING:
      view = <CircularProgress />;
      break;

    case Const.LIST_STATUS_ERROR:
      view = <Warning>Cannot get entries from the API</Warning>;
      break;

    case Const.LIST_STATUS_NO_ENTRY:
      view = <Warning>You haven't created any items yet!</Warning>;
      break;

    case Const.LIST_STATUS_SHOW:
      console.log("Const.LIST_STATUS_SHOW");
      const entries = props.entries.map((currentEntry, index) => (
        <Grid key={currentEntry.id} item xs={12} sm={6} lg={4} xl={3}>
          <EntryExcerptCard
            entry={currentEntry}
            onDelete={() => props.onEntryDeleteClicked(currentEntry.id)}
            onOpen={() => props.onEntryOpenClicked(currentEntry.id)}
          ></EntryExcerptCard>
        </Grid>
      ));
      view = (
        <Grid
          container
          spacing={2}
          style={{
            padding: 24,
            flexGrow: 0,
            maxWidth: "100%",
            flexBasis: "100%",
          }}
        >
          {entries}
        </Grid>
      );
      break;

    default:
      break;
  }
  return (
    <>
      {view}
      <AddScreenButton clicked={props.onAddScreenButtonClicked} />
      <AddDummyEntryButton clicked={props.onAddDummyEntryClicked} />
    </>
  );
};

export default EntryList;
