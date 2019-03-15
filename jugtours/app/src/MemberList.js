import React, { Component } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom';

class MemberList extends Component {

    constructor(props) {
        super(props);
        this.state = {members: [], isLoading: true};
        this.remove = this.remove.bind(this);
    }

    componentDidMount() {
        this.setState({isLoading: true});

        fetch('api/members')
            .then(response => response.json())
            .then(data => this.setState({members: data, isLoading: false}));
    }

    async remove(id) {
        await fetch(`/api/member/${id}`, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(() => {
            let updatedMembers = [...this.state.members].filter(i => i.id !== id);
            this.setState({members: updatedMembers});
        });
    }

    render() {
        const {members, isLoading} = this.state;

        if (isLoading) {
            return <p>Loading...</p>;
        }

        const memberList = members.map(member => {

            return <tr key={member.id}>
                <td style={{whiteSpace: 'nowrap'}}>{member.name}</td>
                <td>{member.name}</td>
                <td>{member.password}</td>
                <td>{member.email}</td>
                <td>
                    <ButtonGroup>
                        <Button size="sm" color="primary" tag={Link} to={"/members/" + member.id}>Edit</Button>
                        <Button size="sm" color="danger" onClick={() => this.remove(member.id)}>Delete</Button>
                    </ButtonGroup>
                </td>
            </tr>
        });

        return (
            <div>
                <AppNavbar/>
                <Container fluid>
                    <div className="float-right">
                        <Button color="success" tag={Link} to="/members/new">Add Member</Button>
                    </div>
                    <h3>My JUG Tour</h3>
                    <Table className="mt-4">
                        <thead>
                        <tr>
                            <th width="20%">id</th>
                            <th width="20%">name</th>
                            <th>Events</th>
                            <th width="10%">Password</th>
                        </tr>
                        </thead>
                        <tbody>
                        {memberList}
                        </tbody>
                    </Table>
                </Container>
            </div>
        );
    }
}

export default MemberList;
