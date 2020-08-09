import React, { Component } from "react";
import EntryList from "./EntryList";
import API from "../../api/Api";
import * as Const from "../../util/Constants";
import * as Helpers from "../../util/Helpers";

class EntriesMain extends Component {
  constructor(props) {
    super(props);
    this.state = {
      screen: Const.SCREEN_LIST,
      status: Const.LIST_STATUS_LOADING,
      entries: [],
      opened: {
        id: "",
        title: "",
        content: "",
      },
    };
  }

  componentDidMount() {
    this.getEntries();
  }

  getEntries = () => {
    API.get("/")
      .then((res) => {
        this.updateStateWithEntries(res.data);
        const currentStatus =
          res.data.length === 0
            ? Const.LIST_STATUS_NO_ENTRY
            : Const.LIST_STATUS_SHOW;
        this.setState({ status: currentStatus });
      })
      .catch(() => {
        this.setState({ status: Const.LIST_STATUS_ERROR });
      });
  };

  updateStateWithEntries = (entries) => {
    this.setState({
      entries: entries.map((item) => {
        return {
          id: item.id,
          title: item.title,
          content: Helpers.excerpt(item.content),
        };
      }),
    });
  };

  handleDeleteEntryClick = (id) => {
    API.delete("/" + id).then(() => this.getEntries());
  };

  handleOpenEntryClick = (id) => {
    API.get("/" + id).then((resp) => {
      const data = resp.data;
      this.setState({
        screen: Const.SCREEN_VIEW,
        opened: {
          id: data.id,
          title: data.title,
          content: data.content,
        },
      });
    });
  };

  render() {
    switch (this.state.screen) {
      case Const.SCREEN_LIST:
        return (
          <EntryList
            status={this.state.status}
            entries={this.state.entries}
            onEntryDelete={this.handleDeleteEntryClick}
            onEntryOpen={this.handleOpenEntryClick}
          />
        );

      default:
        return "TODO";
    }
  }
}

export default EntriesMain;
