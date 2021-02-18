import React, { useState, useEffect } from "react";
import axios from "axios";
import DisplayList from "../../components/table/DisplayList";
import { useDataLayerValue } from "../../dataLayer/DataLayer";
import * as promoService from "../../services/promotionService";
import Popup from "../../components/popup/Popup";
import Delete from "../../components/deleteItem/Delete";

import {
  makeStyles,
  TableRow,
  TableCell,
  IconButton,
  Button,
} from "@material-ui/core";

import AddIcon from "@material-ui/icons/Add";
import CloseIcon from "@material-ui/icons/Close";
import EditOutlinedIcon from "@material-ui/icons/EditOutlined";
import EditAddPromotion from "../../components/form/EditAddPromotion";

const useStyles = makeStyles((theme) => ({
  newButton: {
    position: "absolute",
    right: "10px",
  },
}));

const headCells = [
  { id: "promoCode", label: "Promo Code" },
  { id: "discount", label: "Discount" },
  { id: "promoDescription", label: "Description" },
  { id: "imageUri", label: "Image" },
  { id: "createdAt", label: "Start" },
  { id: "endAt", label: "End" },
  { id: "actions", label: "Actions", disableSorting: true },
];

function Promotion() {
  const [{ token, api }] = useDataLayerValue();
  const classes = useStyles();
  const [promotions, setPromotions] = useState([]);
  const [recordForAdd, setRecordForAdd] = useState(null);
  const [recordForEdit, setRecordForEdit] = useState(null);
  const [recordForDelete, setRecordForDelete] = useState(null);
  const [openPopupEdit, setOpenPopupEdit] = useState(false);
  const [openPopupDelete, setOpenPopupDelete] = useState(false);
  const [openPopupAdd, setOpenPopupAdd] = useState(false);
  const [timesReload, setTimesReload] = useState(0);
  const [page, setPage] = React.useState(0);
  const [rowsPerPage, setRowsPerPage] = React.useState(5);

  const handleChangePage = (event, newPage) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = (event) => {
    setRowsPerPage(parseInt(event.target.value, 10));
    setPage(0);
  };

  const authAxios = axios.create({
    baseURL: api,
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });

  const fetchPromotion = async () => {
    await promoService
      .getPromotion(authAxios)
      .then((res) => {
        console.log(res);
        setPromotions(res);
      })
      .catch((err) => {
        console.log(err);
      });
  };

  useEffect(() => {
    if (token) {
      fetchPromotion();
    } else console.log("no token");
  }, [timesReload]);

  const _addPromotion = (promotion) => {
    promoService
      .addPromotions(authAxios, promotion)
      .then(() => {
        setOpenPopupAdd(false);
        setTimesReload(timesReload + 1);
      })
      .catch((err) => {
        console.log(err);
      });
  };

  const editPromotion = (promotion) => {
    promoService
      .updatePromotions(authAxios, promotion)
      .then(() => {
        setOpenPopupEdit(false);
        setTimesReload(timesReload + 1);
      })
      .catch((err) => {
        console.log(err);
      });
  };

  const deletePromotion = (promotion) => {
    promoService
      .deletePromotions(authAxios, promotion)
      .then(() => {
        setOpenPopupDelete(false);
        setTimesReload(timesReload + 1);
      })
      .catch((err) => {
        console.log(err);
      });
  };

  const addBtn = () => {
    return (
      <Button
        variant="outlined"
        startIcon={<AddIcon />}
        className={classes.newButton}
        onClick={() => {
          setRecordForAdd({
            promoCode: "",
            discount: "",
            promoDescription: "",
            imageUri: "",
          });
          setOpenPopupAdd(true);
        }}
      >
        Add New
      </Button>
    );
  };

  return (
    <div>
      <DisplayList
        headCells={headCells}
        addBtn={addBtn()}
        title={"Promotions"}
        data={promotions}
        page={page}
        rowsPerPage={rowsPerPage}
        handleChangePage={handleChangePage}
        handleChangeRowsPerPage={handleChangeRowsPerPage}
      >
        {(rowsPerPage > 0
          ? promotions.slice(
              page * rowsPerPage,
              page * rowsPerPage + rowsPerPage
            )
          : promotions
        ).map((promo) => {
          return (
            <React.Fragment>
              <TableRow key={promo.id}>
                <TableCell>{promo.promoCode}</TableCell>
                <TableCell>{promo.discount}</TableCell>
                <TableCell>{promo.promoDescription}</TableCell>
                <TableCell>{promo.imageUri}</TableCell>
                <TableCell>{promo.createdAt}</TableCell>
                <TableCell>{promo.endAt}</TableCell>

                <TableCell>
                  <IconButton
                    color="primary"
                    onClick={async () => {
                      await setRecordForEdit(promo);
                      setOpenPopupEdit(true);
                    }}
                  >
                    <EditOutlinedIcon fontSize="small" />
                  </IconButton>
                  <IconButton
                    color="secondary"
                    onClick={async () => {
                      await setRecordForDelete(promo);
                      setOpenPopupDelete(true);
                    }}
                  >
                    <CloseIcon fontSize="small" />
                  </IconButton>
                </TableCell>
              </TableRow>
            </React.Fragment>
          );
        })}
      </DisplayList>

      <Popup
        title="Promotions"
        openPopup={openPopupAdd}
        setOpenPopup={setOpenPopupAdd}
      >
        <EditAddPromotion
          editPromotion={_addPromotion}
          recordForEdit={recordForAdd}
        />
      </Popup>

      <Popup
        title="Promotions"
        openPopup={openPopupEdit}
        setOpenPopup={setOpenPopupEdit}
      >
        <EditAddPromotion
          editPromotion={editPromotion}
          recordForEdit={recordForEdit}
        />
      </Popup>

      <Popup
        title="Delete"
        openPopup={openPopupDelete}
        setOpenPopup={setOpenPopupDelete}
      >
        <Delete
          deleteRecord={deletePromotion}
          recordForDelete={recordForDelete}
          setOpenPopupDelete={setOpenPopupDelete}
          name={recordForDelete ? recordForDelete.promoCode : "Promotion"}
        />
      </Popup>
    </div>
  );
}

export default Promotion;
