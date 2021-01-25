import React, { useState } from "react";
import {
  Paper,
  makeStyles,
  TableBody,
  TableRow,
  TableCell,
  Table,
  TableHead,
  TablePagination,
  TableSortLabel,
  IconButton,
  Toolbar,
  InputAdornment,
  TextField,
  Button,
} from "@material-ui/core";

import CloseIcon from "@material-ui/icons/Close";
import EditOutlinedIcon from "@material-ui/icons/EditOutlined";
import AddIcon from "@material-ui/icons/Add";
import { Search } from "@material-ui/icons";

const useStyles = makeStyles((theme) => ({
  pageContent: {
    margin: theme.spacing(5),
    padding: theme.spacing(3),
  },
  searchInput: {
    width: "75%",
  },
  newButton: {
    position: "absolute",
    right: "10px",
  },
  table: {
    marginTop: theme.spacing(2),
    "& thead th": {
      // fontWeight: "600",
      color: theme.palette.primary.main,
      // backgroundColor: theme.palette.primary.light,
    },
    "& tbody td": {
      // fontWeight: "300",
    },
    "& tbody tr:hover": {
      // backgroundColor: "#fffbf2",
      cursor: "pointer",
    },
  },
}));

const headCells = [
  { id: "username", label: "username" },
  //   { id: "firstname", label: "firstname" },
  //   { id: "lastname", label: "lastname" },
  //   { id: "email", label: "email" },
  //   { id: "phoneNumber", label: "phoneNumber" },
  //   { id: "address", label: "address" },
  { id: "actions", label: "Actions", disableSorting: true },
];

function DisplayList(props) {
  const classes = useStyles();
  const [recordForEdit, setRecordForEdit] = useState(null);
  const [records, setRecords] = useState(props.data);
  const [filterFn, setFilterFn] = useState({
    fn: (users) => {
      return users;
    },
  });
  const [openPopup, setOpenPopup] = useState(false);

  const pages = [5, 10, 25];
  const [page, setPage] = useState(0);
  const [rowsPerPage, setRowsPerPage] = useState(pages[page]);
  const [order, setOrder] = useState();
  const [orderBy, setOrderBy] = useState();

  const openInPopup = (user) => {
    setRecordForEdit(user);
    setOpenPopup(true);
  };

  const handleSortRequest = (cellId) => {
    const isAsc = orderBy === cellId && order === "asc";
    setOrder(isAsc ? "desc" : "asc");
    setOrderBy(cellId);
  };

  const handleChangePage = (event, newPage) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = (event) => {
    setRowsPerPage(parseInt(event.target.value, 10));
    setPage(0);
  };

  const handleSearch = (e) => {
    let target = e.target;
    setFilterFn({
      fn: (users) => {
        if (target.value == "") return users;
        else
          return users.filter((x) =>
            x.fullName.toLowerCase().includes(target.value)
          );
      },
    });
  };

  console.log();
  return (
    <>
      <Paper className={classes.pageContent}>
        <Toolbar>
          <TextField
            label="Search "
            className={classes.searchInput}
            InputProps={{
              startAdornment: (
                <InputAdornment position="start">
                  <Search />
                </InputAdornment>
              ),
            }}
            onChange={handleSearch}
          />
          <Button
            variant="outlined"
            startIcon={<AddIcon />}
            className={classes.newButton}
            onClick={() => {
              setOpenPopup(true);
              setRecordForEdit(null);
            }}
          >
            Add New
          </Button>
        </Toolbar>
        <Table className={classes.table}>
          <TableHead>
            <TableRow>
              {headCells.map((headCell) => (
                <TableCell
                  key={headCell.id}
                  sortDirection={orderBy === headCell.id ? order : false}
                >
                  {headCell.disableSorting ? (
                    headCell.label
                  ) : (
                    <TableSortLabel
                      active={orderBy === headCell.id}
                      direction={orderBy === headCell.id ? order : "asc"}
                      onClick={() => {
                        handleSortRequest(headCell.id);
                      }}
                    >
                      {headCell.label}
                    </TableSortLabel>
                  )}
                </TableCell>
              ))}
            </TableRow>
          </TableHead>
          <TableBody>
            {props.data.map((user) => {
              <TableRow key={user.id}>
                <TableCell>{user.username}</TableCell>
                {/* <TableCell>{user.firstname}</TableCell>
                <TableCell>{user.lastname}</TableCell>
                <TableCell>{user.email}</TableCell>
                <TableCell>{user.phoneNumber}</TableCell>
                <TableCell>{user.address}</TableCell> */}

                <TableCell>
                  <IconButton
                    color="primary"
                    onClick={() => {
                      openInPopup(user);
                    }}
                  >
                    <EditOutlinedIcon fontSize="small" />
                  </IconButton>
                  <IconButton color="secondary">
                    <CloseIcon fontSize="small" />
                  </IconButton>
                </TableCell>
              </TableRow>;
            })}
          </TableBody>
        </Table>
        <TablePagination
          component="div"
          page={page}
          rowsPerPageOptions={pages}
          rowsPerPage={rowsPerPage}
          count={props.data.length}
          onChangePage={handleChangePage}
          onChangeRowsPerPage={handleChangeRowsPerPage}
        />
      </Paper>
    </>
  );
}

export default DisplayList;
