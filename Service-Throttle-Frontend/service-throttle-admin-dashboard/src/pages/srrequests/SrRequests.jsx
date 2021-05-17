import React, { useState, useEffect } from "react";
import axios from "axios";
import DisplayList from "../../components/table/DisplayList";
import { useDataLayerValue } from "../../dataLayer/DataLayer";
import Popup from "../../components/popup/Popup";
import Delete from "../../components/deleteItem/Delete";
import * as srrequest from "../../services/srrequests";

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
import Link from "@material-ui/core/Link";

const useStyles = makeStyles((theme) => ({
  newButton: {
    position: "absolute",
    right: "10px",
  },
}));

const headCells = [
  { id: "vehicleService", label: "Service" },
  { id: "when", label: "Date" },
  { id: "customer", label: "Customer" },
  { id: "customerVehicle", label: "C Vehicle" },
  { id: "status", label: "Status" },
  { id: "discount", label: "Discount" },
  { id: "total", label: "Total" },
  { id: "actions", label: "Actions", disableSorting: true },
];

function SrRequests() {
  const [{ token, api }] = useDataLayerValue();
  const [srRequests, setsrRequests] = useState([]);
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

  const fetchSrRequests = async () => {
    await srrequest
      .getrequests(authAxios)
      .then((res) => {
        console.log(res);
        setsrRequests(res);
      })
      .catch((err) => {
        console.log(err);
      });
  };

  useEffect(() => {
    if (token) {
      fetchSrRequests();
    } else console.log("no token");
  }, [timesReload]);

  return (
    <div>
      <DisplayList
        headCells={headCells}
        // addBtn={addBtn()}
        title={"ST Requests"}
        data={srRequests}
        page={page}
        rowsPerPage={rowsPerPage}
        handleChangePage={handleChangePage}
        handleChangeRowsPerPage={handleChangeRowsPerPage}
      >
        {(rowsPerPage > 0
          ? srRequests.slice(
              page * rowsPerPage,
              page * rowsPerPage + rowsPerPage
            )
          : srRequests
        ).map((requests) => {
          return (
            <React.Fragment>
              <TableRow key={requests.id}>
                <TableCell>
                  {requests.vehicleServices[0].vehicleServiceName}
                </TableCell>
                <TableCell>{requests.whenWant}</TableCell>
                <TableCell>
                  <Link
                    component="button"
                    variant="body3"
                    onClick={() => {
                      console.info(
                        requests.vehicleServices[0].vehicleServiceName
                      );
                    }}
                  >
                    {requests.customer.username}
                  </Link>
                </TableCell>
                <TableCell>
                  {requests.customerVehicle.vehicleMake.make +
                    " " +
                    requests.customerVehicle.vehicleModel.model}
                </TableCell>
                <TableCell>{requests.requestStatus}</TableCell>
                <TableCell>{requests.discount + "%"}</TableCell>
                <TableCell>{requests.totalCost}</TableCell>
                <TableCell>
                  <IconButton
                    color="primary"
                    onClick={async () => {
                      // await setRecordForEdit(requests);
                      // setOpenPopupEdit(true);
                    }}
                  >
                    <EditOutlinedIcon fontSize="small" />
                  </IconButton>
                  <IconButton
                    color="secondary"
                    onClick={async () => {
                      // await setRecordForDelete(requests);
                      // setOpenPopupDelete(true);
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
    </div>
  );
}

export default SrRequests;
