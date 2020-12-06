import React from "react";
import axios from 'axios';
import { Button, TextField, Typography, Container} from "@material-ui/core";

const api = 'http://localhost:8081/st'
let users;
let token = localStorage.getItem('token');
let authAxios = axios.create({
  baseURL: api,
  headers:{
    Authorization:`Bearer ${token}`
  }
});

export class Login extends React.Component {
    constructor(props) {
      super(props);
      this.state = {
        username:null,
        password:null
      }
    }

    userLogin = async () => {
      try{
        const res = await axios.post(`${api}/login`,this.state)
        token = res.data.jwttoken
        localStorage.setItem('token', token);
      }
      catch(err){
        
      }
      console.log(token)
      
    }
 
    getUsers = async () => {
      users = await authAxios.get(`/users`)
      console.log(users)
    }
  
    render() {
      return (
        <Container maxWidth="sm">
        <div className="base-container" ref={this.props.containerRef}>
          <Typography variant="h4" gutterBottom>Service Throttle</Typography>
          <form >
            <div className="input">
              <div className="input-username">                
                <TextField
                  type="text"                 
                  label="Username"
                  margin="dense"
                  variant="outlined"
                  onChange={(event)=>{this.setState({username:event.target.value})}}/></div>
              <div className="input-password">                  
                <TextField
                    type="password"                 
                    label="Password"
                    margin="dense"
                    variant="outlined"
                    onChange={(event)=>{this.setState({password:event.target.value})}}/></div>
            </div>    
            <div className="btn-login">
              <Button 
                variant="contained" 
                color="primary" 
                component="span"
                style={{
                  backgroundColor: "#1167b1",
              }}
                onClick={this.userLogin}>LOGIN</Button>
            </div>
          </form>
        </div>
        </Container>
      );
    }
  }