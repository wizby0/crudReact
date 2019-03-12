import React, { Component } from 'react';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';
import AppNavbar from './AppNavbar';
import { Link } from 'react-router-dom';

class PostList extends Component {

    constructor(props) {
        super(props);
        this.state = {posts: [], isLoading: true};
        this.remove = this.remove.bind(this);
    }

    componentDidMount() {
        this.setState({isLoading: true});

        fetch('api/posts')
            .then(response => response.json())
            .then(data => this.setState({posts: data, isLoading: false}));
    }

    async remove(id) {
        await fetch(`/api/post/${id}`, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(() => {
            let updatedPosts = [...this.state.posts].filter(i => i.id !== id);
            this.setState({posts: updatedPosts});
        });
    }

    render() {
        const {posts, isLoading} = this.state;

        if (isLoading) {
            return <p>Loading...</p>;
        }

        const postList = posts.map(post => {
            const groupname = `${post.groupname || ''} ${post.city || ''} ${post.stateOrProvince || ''}`;
            return <tr key={post.id}>
                <td style={{whiteSpace: 'nowrap'}}>{post.name}</td>
                <td>{groupname}</td>
                <td>{post.headline}</td>
                <td>{post.headline}</td>
                <td>{post.contpassword}</td>
                <td>
                    <ButtonGroup>
                        <Button size="sm" color="primary" tag={Link} to={"/posts/" + post.id}>Edit</Button>
                        <Button size="sm" color="danger" onClick={() => this.remove(post.id)}>Delete</Button>
                    </ButtonGroup>
                </td>
            </tr>
        });

        return (
            <div>
                <AppNavbar/>
                <Container fluid>
                    <div className="float-right">
                        <Button color="success" tag={Link} to="/posts/new">Add Post</Button>
                    </div>
                    <h3>My JUG Tour</h3>
                    <Table className="mt-4">
                        <thead>
                        <tr>
                            <th width="20%">Name</th>
                            <th width="20%">Location</th>
                            <th>Events</th>
                            <th width="10%">Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        {postList}
                        </tbody>
                    </Table>
                </Container>
            </div>
        );
    }
}

export default PostList;
