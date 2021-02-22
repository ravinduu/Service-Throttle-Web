import React from "react";
import { makeStyles } from "@material-ui/core";
import Dialog from "@material-ui/core/Dialog";
import DialogContent from "@material-ui/core/DialogContent";
import DialogContentText from "@material-ui/core/DialogContentText";
import DialogTitle from "@material-ui/core/DialogTitle";

function Popup(props) {
  const { title, actions, text, children, openPopup, setOpenPopup } = props;

  const useStyles = makeStyles((theme) => ({
    dialogWrapper: {
      padding: theme.spacing(2),
      position: "absolute",
      top: theme.spacing(5),
    },
    dialogTitle: {
      paddingRight: "0px",
    },
  }));

  return (
    <div>
      <Dialog
        open={openPopup}
        onClose={() => {
          setOpenPopup(false);
        }}
        aria-labelledby="alert-dialog-title"
        aria-describedby="alert-dialog-description"
      >
        <DialogTitle id="alert-dialog-title">{title}</DialogTitle>
        <DialogContent>
          <DialogContentText id="alert-dialog-description">
            {text}
          </DialogContentText>
          {children}
        </DialogContent>
      </Dialog>
    </div>
  );
}

export default Popup;
