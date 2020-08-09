import React, { Component } from "react";
import EntryList from "./EntryList";
import API from "../../api/Api";
import axios from "axios";
import * as Const from "../../util/Constants";
import * as Helpers from "../../util/Helpers";
import dummyEntryJSON from "../../data/entries.json";
import AddEntryForm from "./AddEntryForm";
import EntryDetails from "./EntryDetails";

class EntriesMain extends Component {
  constructor(props) {
    super(props);
    this.state = {
      screen: Const.SCREEN_LIST,
      status: Const.LIST_STATUS_LOADING,
      entries: [],
      newTitle: "",
      newContent: "",
      searchWord: "",
      lastSearchWord: "",
      searchResult: null,
      showSearchResult: false,
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

  handleAddDummyEntryClick = () => {
    axios
      .all(
        dummyEntryJSON.entries.map((entry) =>
          API.post("/", { title: entry.title, content: entry.content })
        )
      )
      .then(
        axios.spread(() => {
          this.getEntries();
        })
      );
  };

  handleAddScreenButtonClick = () => {
    this.setState({ screen: Const.SCREEN_ADD });
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

  handleAddEntryActionButtonClick = () => {
    const { newTitle, newContent } = this.state;
    API.post("", { title: newTitle, content: newContent }).then((response) => {
      this.setState({
        newTitle: "",
        newContent: "",
        screen: Const.SCREEN_LIST,
      });
      this.getEntries();
    });
  };

  handleBackButtonClicked = () => {
    this.setState({
      screen: Const.SCREEN_LIST,
      searchResult: null,
      newTitle: "",
      newContent: "",
      searchWord: "",
      lastSearchWord: "",
    });
  };

  handleSearchWordButtonClicked = () => {
    const word = this.state.searchWord;
    const entryId = this.state.opened.id;
    this.setState({ lastSearchWord: word });
    API.get("/" + entryId + "/search/" + word).then((response) => {
      this.setState({ searchResult: response.data, showSearchResult: true });
    });
  };

  handleTextInputChange = (input) => (e) => {
    this.setState({ [input]: e.target.value });
  };

  render() {
    switch (this.state.screen) {
      case Const.SCREEN_LIST:
        return (
          <EntryList
            status={this.state.status}
            entries={this.state.entries}
            onEntryDeleteClicked={this.handleDeleteEntryClick}
            onEntryOpenClicked={this.handleOpenEntryClick}
            onAddScreenButtonClicked={this.handleAddScreenButtonClick}
            onAddDummyEntryClicked={this.handleAddDummyEntryClick}
          />
        );

      case Const.SCREEN_ADD:
        return (
          <AddEntryForm
            newTitle={this.state.newTitle}
            newContent={this.state.newContent}
            handleTextInputChange={this.handleTextInputChange}
            addEntryClicked={this.handleAddEntryActionButtonClick}
            backButtonClicked={this.handleBackButtonClicked}
          />
        );

      case Const.SCREEN_VIEW:
        return (
          <EntryDetails
            entryTitle={this.state.opened.title}
            entryContent={this.state.opened.content}
            searchResult={this.state.searchResult}
            showSearchResult={this.state.showSearchResult}
            searchWord={this.state.searchWord}
            lastSearchWord={this.state.lastSearchWord}
            handleTextInputChange={this.handleTextInputChange}
            searchWordClicked={this.handleSearchWordButtonClicked}
            backButtonClicked={this.handleBackButtonClicked}
          />
        );

      default:
        return "TODO";
    }
  }
}

export default EntriesMain;
