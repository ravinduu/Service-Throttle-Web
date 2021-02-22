import React from "react";
import Button from "@material-ui/core/Button";
import DialogActions from "@material-ui/core/DialogActions";
import DialogContent from "@material-ui/core/DialogContent";
import DialogContentText from "@material-ui/core/DialogContentText";

function Delete(props) {
  const { deleteRecord, recordForDelete, setOpenPopupDelete, name } = props;

  const cancel = () => {
    setOpenPopupDelete(false);
  };

  const deleteItem = () => {
    deleteRecord(recordForDelete);
  };

  return (
    <div>
      <DialogContent>
        <DialogContentText>
          Are you sure you want to delete @{name} ?
        </DialogContentText>
      </DialogContent>
      <DialogActions>
        <Button variant="outlined" onClick={cancel} color="primary">
          Cancel
        </Button>
        <Button variant="outlined" onClick={deleteItem} color="secondary">
          Delete
        </Button>
      </DialogActions>
    </div>
  );
}

export default Delete;
