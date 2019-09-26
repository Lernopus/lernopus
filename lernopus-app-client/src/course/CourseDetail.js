import React, { Component } from 'react';
import LoadingIndicator  from '../common/LoadingIndicator';
import Chips, { Chip } from 'react-chips'
import './CourseDetail.css';
import NotFound from '../common/NotFound';
import ServerError from '../common/ServerError';
import { getCourseDetails } from '../util/APIUtils';
import { Avatar, Tabs } from 'antd';
import { Link } from 'react-router-dom';
import { getAvatarColor } from '../util/Colors';
import { formatDateTime } from '../util/Helpers';
import theme from './ReactChipTheme';

class CourseDetail extends Component {
    constructor(props) {
        super(props);
        this.state = {
            course: [],
            isLoading: true
        };
        this.loadCourseDetails = this.loadCourseDetails.bind(this);
    }

    loadCourseDetails(learnCourseId) {
        this.setState({
            isLoading: true
        });

        getCourseDetails(learnCourseId)
        .then(response => {
            this.setState({
                course: response,
                isLoading: false
            });
        }).catch(error => {
            if(error.status === 404) {
                this.setState({
                    notFound: true,
                    isLoading: false
                });
            } else {
                this.setState({
                    serverError: true,
                    isLoading: false
                });        
            }
        });        
    }
      
    componentDidMount() {
        const learnCourseId = this.props.match.params.learnCourseId;
        this.loadCourseDetails(learnCourseId);
    }

    render() {
        if(this.state.isLoading) {
            return <LoadingIndicator />;
        }

        if(this.state.notFound) {
            return <NotFound />;
        }

        if(this.state.serverError) {
            return <ServerError />;
        }

        return (
            <div className="new-course-container">
                    <div className="course-creator-info">
                        <Link className="creator-link" to={`/learnCourseId/${this.state.course.createdBy.laUserName}`}>
                            <Avatar className="course-creator-avatar" 
                                style={{ backgroundColor: getAvatarColor(this.state.course.createdBy.laUserFullName)}} >
                                {this.state.course.createdBy.laUserFullName[0].toUpperCase()}
                            </Avatar>
                            <span className="course-creator-name">
                                {this.state.course.createdBy.laUserFullName}
                            </span>
                            <span className="course-creator-username">
                                @{this.state.course.createdBy.laUserName}
                            </span>
                            <span className="course-creation-date">
                                {formatDateTime(this.state.course.laCreatedAt)}
                            </span>
                        </Link>
                    </div>
                    <div className="new-course-content">
                        <div className="course-question">
                            {this.state.course.laLearnCourseName}
                        </div>
                        <div  id = 'tech-tag-div'>
                            <Chips id = 'tech-tag'
                                value={this.state.course.laTechTag}
                                theme = {theme}
                            />
                        </div>
                    </div>
                    <div>
                    <span dangerouslySetInnerHTML={{__html: this.state.course.laCourseContentHtml}} />
                    </div>

                    {
                        this.state.course.laLearnAttachments.length > 0
                        ? <div className='files-list'>
                          <ul>{this.state.course.laLearnAttachments.map((file) =>
                            <li className='files-list-item' key={file.laAttachName}>
                              <div className='files-list-item-preview'>
                                {file.laAttachPreview.type === 'image'
                                ? <img className='files-list-item-preview-image' src={file.laAttachPreview.url} /> // eslint-disable-line
                                : <div className='files-list-item-preview-extension'>{file.laAttachExtension}</div>}
                              </div>
                              <div className='files-list-item-content'>
                                <div className='files-list-item-content-item files-list-item-content-item-1'>{file.laAttachName}</div>
                                <div className='files-list-item-content-item files-list-item-content-item-2'>{file.laAttachmentSize}</div>
                              </div>
                            </li>
                          )}</ul>
                        </div>
                        : null
                      }
                        
            </div>
            // <div className="profile">
            //     { 
            //         this.state.user ? (
            //             <div className="user-profile">
            //                 <div className="user-details">
            //                     <div className="user-avatar">
            //                         <Avatar className="user-avatar-circle" style={{ backgroundColor: getAvatarColor(this.state.user.name)}}>
            //                             {this.state.user.name[0].toUpperCase()}
            //                         </Avatar>
            //                     </div>
            //                     <div className="user-summary">
            //                         <div className="full-name">{this.state.user.name}</div>
            //                         <div className="username">@{this.state.user.username}</div>
            //                         <div className="user-joined">
            //                             Joined {formatDate(this.state.user.joinedAt)}
            //                         </div>
            //                     </div>
            //                 </div>
            //                 <div className="user-course-details">    
            //                     <Tabs defaultActiveKey="1" 
            //                         animated={false}
            //                         tabBarStyle={tabBarStyle}
            //                         size="large"
            //                         className="profile-tabs">
            //                         <TabPane tab={`${this.state.user.courseCount} courses`} key="1">
            //                             <courseList username={this.props.match.params.username} type="USER_CREATED_courseS" />
            //                         </TabPane>
            //                         <TabPane tab={`${this.state.user.voteCount} Votes`}  key="2">
            //                             <courseList username={this.props.match.params.username} type="USER_VOTED_courseS" />
            //                         </TabPane>
            //                     </Tabs>
            //                 </div>  
            //             </div>  
            //         ): null               
            //     }
            // </div>
        );
    }
}

export default CourseDetail;