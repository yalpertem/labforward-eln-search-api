import React from "react";
import Card from "@material-ui/core/Card";
import CardActions from "@material-ui/core/CardActions";
import CardContent from "@material-ui/core/CardContent";
import Button from "@material-ui/core/Button";
import Typography from "@material-ui/core/Typography";

const EntryCard = (props) => {
  return (
    <div>
      {props.entry ? (
        <Card>
          <CardContent>
            <Typography gutterBottom variant="h4" component="h2">
              {props.entry.title}
            </Typography>
            <Typography component="p">{props.entry.content}</Typography>
          </CardContent>
          <CardActions>
            <Button size="small" color="primary" onClick={props.onOpen}>
              View
            </Button>
            <Button onClick={props.onDelete} size="small" color="secondary">
              Delete
            </Button>
          </CardActions>
        </Card>
      ) : null}
      <Card></Card>
    </div>
  );
};

export default EntryCard;
