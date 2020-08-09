import React from "react";
import Warning from "../alert/Warning";
import Grid from "@material-ui/core/Grid";
import EntryExcerptCard from "./EntryExcerptCard";
import * as Const from "../../util/Constants";
import { CircularProgress } from "@material-ui/core";

const EntryList = (props) => {
  switch (props.status) {
    case Const.LIST_STATUS_LOADING:
      return <CircularProgress />;
    case Const.LIST_STATUS_ERROR:
      return <Warning>Cannot get entries from the API</Warning>;
    case Const.LIST_STATUS_NO_ENTRY:
      return <Warning>You haven't created any items yet!</Warning>;
    case Const.LIST_STATUS_SHOW:
      console.log("Const.LIST_STATUS_SHOW");
      const entries = props.entries.map((currentEntry, index) => (
        <Grid key={currentEntry.id} item xs={12} sm={6} lg={4} xl={3}>
          <EntryExcerptCard
            entry={currentEntry}
            onDelete={() => this.props.onEntryDelete(currentEntry.id)}
            onOpen={() => this.props.onEntryOpen(currentEntry.id)}
          ></EntryExcerptCard>
        </Grid>
      ));
      return (
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
    default:
      return "";
  }
};

export default EntryList;
