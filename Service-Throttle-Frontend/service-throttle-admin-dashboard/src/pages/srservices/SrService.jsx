import React, { useState, useEffect } from "react";
import axios from "axios";
import DisplayList from "../../components/table/DisplayList";
import { useDataLayerValue } from "../../dataLayer/DataLayer";
import Popup from "../../components/popup/Popup";
import Delete from "../../components/deleteItem/Delete";
import * as srservice from "../../services/srserviceservice";
import EditAddSrService from "../../components/form/EditAddSrService";

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

const useStyles = makeStyles((theme) => ({
  newButton: {
    position: "absolute",
    right: "10px",
  },
}));

const headCells = [
  { id: "vehicleServiceName", label: "Name" },
  { id: "vehicleServicePrice", label: "Price" },
  { id: "vehicleServiceDescription", label: "Description" },
  { id: "vehicleServiceType", label: "Type" },
  { id: "imageURL", label: "Image" },
  { id: "actions", label: "Actions", disableSorting: true },
];

function SrService() {
  const [{ token, api }] = useDataLayerValue();
  const classes = useStyles();
  const [SrServices, setSrServices] = useState([]);
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

  const fetchSrService = async () => {
    await srservice
      .getservice(authAxios)
      .then((res) => {
        console.log(res);
        setSrServices(res);
      })
      .catch((err) => {
        console.log(err);
      });
  };

  useEffect(() => {
    if (token) {
      fetchSrService();
    } else console.log("no token");
  }, [timesReload]);

  const _addSrService = (SrService) => {
    srservice
      .addservices(authAxios, SrService)
      .then(() => {
        setOpenPopupAdd(false);
        setTimesReload(timesReload + 1);
      })
      .catch((err) => {
        console.log(err);
      });
  };

  const editSrService = (SrService) => {
    srservice
      .updateservices(authAxios, SrService)
      .then(() => {
        setOpenPopupEdit(false);
        setTimesReload(timesReload + 1);
      })
      .catch((err) => {
        console.log(err);
      });
  };

  const deleteSrService = (SrService) => {
    srservice
      .deleteservices(authAxios, SrService)
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
            vehicleServiceName: "",
            vehicleServicePrice: "",
            vehicleServiceDescription: "",
            vehicleServiceType: "",
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
        title={"SR Services"}
        data={SrServices}
        page={page}
        rowsPerPage={rowsPerPage}
        handleChangePage={handleChangePage}
        handleChangeRowsPerPage={handleChangeRowsPerPage}
      >
        {(rowsPerPage > 0
          ? SrServices.slice(
              page * rowsPerPage,
              page * rowsPerPage + rowsPerPage
            )
          : SrServices
        ).map((service) => {
          return (
            <React.Fragment>
              <TableRow key={service.id}>
                <TableCell>{service.vehicleServiceName}</TableCell>
                <TableCell>{service.vehicleServicePrice}</TableCell>
                <TableCell>{service.vehicleServiceDescription}</TableCell>
                <TableCell>{service.vehicleServiceType}</TableCell>
                <TableCell>{service.imageURL}</TableCell>
                <TableCell>
                  <IconButton
                    color="primary"
                    onClick={async () => {
                      await setRecordForEdit(service);
                      setOpenPopupEdit(true);
                    }}
                  >
                    <EditOutlinedIcon fontSize="small" />
                  </IconButton>
                  <IconButton
                    color="secondary"
                    onClick={async () => {
                      await setRecordForDelete(service);
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
        title="SR Services"
        openPopup={openPopupAdd}
        setOpenPopup={setOpenPopupAdd}
      >
        <EditAddSrService
          editSrService={_addSrService}
          recordForEdit={recordForAdd}
        />
      </Popup>

      <Popup
        title="SR Services"
        openPopup={openPopupEdit}
        setOpenPopup={setOpenPopupEdit}
      >
        <EditAddSrService
          editSrService={editSrService}
          recordForEdit={recordForEdit}
        />
      </Popup>

      <Popup
        title="Delete"
        openPopup={openPopupDelete}
        setOpenPopup={setOpenPopupDelete}
      >
        <Delete
          deleteRecord={deleteSrService}
          recordForDelete={recordForDelete}
          setOpenPopupDelete={setOpenPopupDelete}
          name={
            recordForDelete ? recordForDelete.vehicleServiceName : "SrService"
          }
        />
      </Popup>
    </div>
  );
}

export default SrService;
