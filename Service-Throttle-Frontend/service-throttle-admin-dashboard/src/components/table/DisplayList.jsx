import React, { useState } from "react";

import Title from "../title/Title";
import "./DisplayList.css";

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
  Toolbar,
  InputAdornment,
  TextField,
} from "@material-ui/core";
import { Search } from "@material-ui/icons";

const useStyles = makeStyles((theme) => ({
  pageContent: {
    margin: theme.spacing(0),
    padding: theme.spacing(0),
  },
  searchInput: {
    width: "75%",
  },
  newButton: {
    position: "absolute",
    right: "10px",
  },
  table: {
    marginTop: theme.spacing(0),
    "& thead th": {
      color: theme.palette.primary.main,
    },
    "& tbody td": {},
    "& tbody tr:hover": {
      cursor: "pointer",
    },
  },
}));

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

  return (
    <div className="userlistComponent">
      <div className="title">
        <Title title={props.title} variant="h2" />
      </div>
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
          {props.addBtn}
        </Toolbar>
        <Table className={classes.table}>
          <TableHead>
            <TableRow>
              {props.headCells.map((headCell) => (
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
          <TableBody>{props.children}</TableBody>
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
    </div>
  );
}

export default DisplayList;
