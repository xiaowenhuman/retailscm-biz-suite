import React from 'react'
import PropTypes from 'prop-types'
import {
  Layout,
  Menu,
  Icon,
  Avatar,
  Dropdown,
  Tag,
  message,
  Spin,
  Breadcrumb,
  AutoComplete,Row, Col,
  Input,Button
} from 'antd'
import TopMenu from '../../launcher/TopMenu'
import DocumentTitle from 'react-document-title'
import { connect } from 'dva'
import { Link, Route, Redirect, Switch } from 'dva/router'
import moment from 'moment'
import groupBy from 'lodash/groupBy'
import { ContainerQuery } from 'react-container-query'
import classNames from 'classnames'
import styles from './RetailStoreOrderPicking.app.less'
import {sessionObject} from '../../utils/utils'

import HeaderSearch from '../../components/HeaderSearch';
import NoticeIcon from '../../components/NoticeIcon';
import GlobalFooter from '../../components/GlobalFooter';


import GlobalComponents from '../../custcomponents';

import PermissionSettingService from '../../permission/PermissionSetting.service'
import appLocaleName from '../../common/Locale.tool'
import BizAppTool from '../../common/BizApp.tool'

const { Header, Sider, Content } = Layout
const { SubMenu } = Menu
const {
  defaultFilteredNoGroupMenuItems,
  defaultFilteredMenuItemsGroup,
  defaultRenderMenuItem,

} = BizAppTool


const filteredNoGroupMenuItems = defaultFilteredNoGroupMenuItems
const filteredMenuItemsGroup = defaultFilteredMenuItemsGroup
const renderMenuItem=defaultRenderMenuItem



const userBarResponsiveStyle = {
  xs: 8,
  sm: 8,
  md: 8,
  lg: 6,
  xl: 6,
  
};


const searchBarResponsiveStyle = {
  xs: 8,
  sm: 8,
  md: 8,
  lg: 12,
  xl: 12,
  
};


const naviBarResponsiveStyle = {
  xs: 8,
  sm: 8,
  md: 8,
  lg: 6,
  xl: 6,
  
};


const query = {
  'screen-xs': {
    maxWidth: 575,
  },
  'screen-sm': {
    minWidth: 576,
    maxWidth: 767,
  },
  'screen-md': {
    minWidth: 768,
    maxWidth: 991,
  },
  'screen-lg': {
    minWidth: 992,
    maxWidth: 1199,
  },
  'screen-xl': {
    minWidth: 1200,
  },
}




class RetailStoreOrderPickingBizApp extends React.PureComponent {
constructor(props) {
    super(props)
     this.state = {
      openKeys: this.getDefaultCollapsedSubMenus(props),
      showSearch: false,
      searchKeyword:''
    }
  }

  componentDidMount() {}
  componentWillUnmount() {
    clearTimeout(this.resizeTimeout)
  }
  onCollapse = (collapsed) => {
    this.props.dispatch({
      type: 'global/changeLayoutCollapsed',
      payload: collapsed,
    })
  }

  getDefaultCollapsedSubMenus = (props) => {
    const currentMenuSelectedKeys = [...this.getCurrentMenuSelectedKeys(props)]
    currentMenuSelectedKeys.splice(-1, 1)
    if (currentMenuSelectedKeys.length === 0) {
      return ['/retailStoreOrderPicking/']
    }
    return currentMenuSelectedKeys
  }
  getCurrentMenuSelectedKeys = (props) => {
    const { location: { pathname } } = props || this.props
    const keys = pathname.split('/').slice(1)
    if (keys.length === 1 && keys[0] === '') {
      return [this.menus[0].key]
    }
    return keys
  }
  
  getNavMenuItems = (targetObject) => {
  

    const menuData = sessionObject('menuData')
    const targetApp = sessionObject('targetApp')
	const {objectId}=targetApp;
  	const userContext = null
    return (
	  <Menu
        theme="dark"
        mode="inline"
        
        onOpenChange={this.handleOpenChange}
        defaultOpenKeys={['firstOne']}
<<<<<<< HEAD
        style={{ width: '256px' }}
=======
        style={{ width: '456px' }}
>>>>>>> ea67698ef1c4e94c89147baaf9f93aa768973fbe
       >
           

             <Menu.Item key="dashboard">
               <Link to={`/retailStoreOrderPicking/${this.props.retailStoreOrderPicking.id}/dashboard`}><Icon type="dashboard" style={{marginRight:"20px"}}/><span>{appLocaleName(userContext,"Dashboard")}</span></Link>
             </Menu.Item>
           
        {filteredNoGroupMenuItems(targetObject,this).map((item)=>(renderMenuItem(item)))}  
        {filteredMenuItemsGroup(targetObject,this).map((groupedMenuItem,index)=>{
          return(
    <SubMenu key={`vg${index}`} title={<span><Icon type="folder" style={{marginRight:"20px"}} /><span>{`${groupedMenuItem.viewGroup}`}</span></span>} >
      {groupedMenuItem.subItems.map((item)=>(renderMenuItem(item)))}  
    </SubMenu>

        )}
        )}

       		
        
           </Menu>
    )
  }
  



  getRetailStoreOrderSearch = () => {
    const {RetailStoreOrderSearch} = GlobalComponents;
    const userContext = null
    return connect(state => ({
      rule: state.rule,
      name: "生超的订单",
      role: "retailStoreOrder",
      data: state._retailStoreOrderPicking.retailStoreOrderList,
      metaInfo: state._retailStoreOrderPicking.retailStoreOrderListMetaInfo,
      count: state._retailStoreOrderPicking.retailStoreOrderCount,
      returnURL: `/retailStoreOrderPicking/${state._retailStoreOrderPicking.id}/dashboard`,
      currentPage: state._retailStoreOrderPicking.retailStoreOrderCurrentPageNumber,
      searchFormParameters: state._retailStoreOrderPicking.retailStoreOrderSearchFormParameters,
      searchParameters: {...state._retailStoreOrderPicking.searchParameters},
      expandForm: state._retailStoreOrderPicking.expandForm,
      loading: state._retailStoreOrderPicking.loading,
      partialList: state._retailStoreOrderPicking.partialList,
      owner: { type: '_retailStoreOrderPicking', id: state._retailStoreOrderPicking.id, 
      referenceName: 'picking', 
      listName: 'retailStoreOrderList', ref:state._retailStoreOrderPicking, 
      listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(RetailStoreOrderSearch)
  }
  getRetailStoreOrderCreateForm = () => {
   	const {RetailStoreOrderCreateForm} = GlobalComponents;
   	const userContext = null
    return connect(state => ({
      rule: state.rule,
      role: "retailStoreOrder",
      data: state._retailStoreOrderPicking.retailStoreOrderList,
      metaInfo: state._retailStoreOrderPicking.retailStoreOrderListMetaInfo,
      count: state._retailStoreOrderPicking.retailStoreOrderCount,
      returnURL: `/retailStoreOrderPicking/${state._retailStoreOrderPicking.id}/list`,
      currentPage: state._retailStoreOrderPicking.retailStoreOrderCurrentPageNumber,
      searchFormParameters: state._retailStoreOrderPicking.retailStoreOrderSearchFormParameters,
      loading: state._retailStoreOrderPicking.loading,
      owner: { type: '_retailStoreOrderPicking', id: state._retailStoreOrderPicking.id, referenceName: 'picking', listName: 'retailStoreOrderList', ref:state._retailStoreOrderPicking, listDisplayName: appLocaleName(userContext,"List")}, // this is for model namespace and
    }))(RetailStoreOrderCreateForm)
  }
  
  getRetailStoreOrderUpdateForm = () => {
    const userContext = null
  	const {RetailStoreOrderUpdateForm} = GlobalComponents;
    return connect(state => ({
      selectedRows: state._retailStoreOrderPicking.selectedRows,
      role: "retailStoreOrder",
      currentUpdateIndex: state._retailStoreOrderPicking.currentUpdateIndex,
      owner: { type: '_retailStoreOrderPicking', id: state._retailStoreOrderPicking.id, listName: 'retailStoreOrderList', ref:state._retailStoreOrderPicking, listDisplayName: appLocaleName(userContext,"List") }, // this is for model namespace and
    }))(RetailStoreOrderUpdateForm)
  }


  

 

  getPageTitle = () => {
    // const { location } = this.props
    // const { pathname } = location
    const title = '双链小超全流程供应链系统'
    return title
  }
 
  buildRouters = () =>{
  	const {RetailStoreOrderPickingDashboard} = GlobalComponents
  	const {RetailStoreOrderPickingPermission} = GlobalComponents
  	const {RetailStoreOrderPickingProfile} = GlobalComponents
  	
  	
  	const routers=[
  	{path:"/retailStoreOrderPicking/:id/dashboard", component: RetailStoreOrderPickingDashboard},
  	{path:"/retailStoreOrderPicking/:id/profile", component: RetailStoreOrderPickingProfile},
  	{path:"/retailStoreOrderPicking/:id/permission", component: RetailStoreOrderPickingPermission},
  	
  	
  	
  	{path:"/retailStoreOrderPicking/:id/list/retailStoreOrderList", component: this.getRetailStoreOrderSearch()},
  	{path:"/retailStoreOrderPicking/:id/list/retailStoreOrderCreateForm", component: this.getRetailStoreOrderCreateForm()},
  	{path:"/retailStoreOrderPicking/:id/list/retailStoreOrderUpdateForm", component: this.getRetailStoreOrderUpdateForm()},
     	
 	 
  	]
  	
  	const {extraRoutesFunc} = this.props;
  	const extraRoutes = extraRoutesFunc?extraRoutesFunc():[]
  	const finalRoutes = routers.concat(extraRoutes)
    
  	return (<Switch>
             {finalRoutes.map((item)=>(<Route key={item.path} path={item.path} component={item.component} />))}    
  	  	</Switch>)
  	
  
  }
 
 
  handleOpenChange = (openKeys) => {
    const latestOpenKey = openKeys.find(key => this.state.openKeys.indexOf(key) === -1)
    this.setState({
      openKeys: latestOpenKey ? [latestOpenKey] : [],
    })
  }
   toggle = () => {
     const { collapsed } = this.props
     this.props.dispatch({
       type: 'global/changeLayoutCollapsed',
       payload: !collapsed,
     })
   }
    logout = () => {
   
    console.log("log out called")
    this.props.dispatch({ type: 'launcher/signOut' })
  }
   render() {
     // const { collapsed, fetchingNotices,loading } = this.props
     const { collapsed } = this.props
     
  
     const targetApp = sessionObject('targetApp')
     const currentBreadcrumb =targetApp?sessionObject(targetApp.id):[];
     const userContext = null
     const renderBreadcrumbText=(value)=>{
     	if(value==null){
     		return "..."
     	}
     	if(value.length < 10){
     		return value
     	}
     
     	return value.substring(0,10)+"..."
     	
     	
     }
     const menuProps = collapsed ? {} : {
       openKeys: this.state.openKeys,
     }
     const renderBreadcrumbMenuItem=(breadcrumbMenuItem)=>{

      return (
      <Menu.Item key={breadcrumbMenuItem.link}>
      <Link key={breadcrumbMenuItem.link} to={`${breadcrumbMenuItem.link}`} className={styles.breadcrumbLink}>
        <Icon type="heart" style={{marginRight:"10px",color:"red"}} />
        {renderBreadcrumbText(breadcrumbMenuItem.name)}
      </Link></Menu.Item>)

     }
     const breadcrumbMenu=()=>{
      const currentBreadcrumb =targetApp?sessionObject(targetApp.id):[];
      return ( <Menu mode="vertical"> 
      {currentBreadcrumb.map(item => renderBreadcrumbMenuItem(item))}
      </Menu>)
  

     }
<<<<<<< HEAD
     const { Search } = Input;
=======
     
     const { Search } = Input;
     const showSearchResult=()=>{

        this.setState({showSearch:true})

     }
     const searchChange=(evt)=>{

      this.setState({searchKeyword :evt.target.value})

    }
    const hideSearchResult=()=>{

      this.setState({showSearch:false})

    }

    const {searchLocalData}=GlobalComponents.RetailStoreOrderPickingBase
	
    
     
     
>>>>>>> ea67698ef1c4e94c89147baaf9f93aa768973fbe
     const layout = (
     <Layout>
 <Header style={{ position: 'fixed', zIndex: 1, width: '100%' }}>
          
        <Row type="flex" justify="start" align="bottom">
        
        <Col {...naviBarResponsiveStyle} >
            <Dropdown overlay= {this.getNavMenuItems(this.props.retailStoreOrderPicking)}>
              <a  className={styles.menuLink}>
                <Icon type="unordered-list" style={{fontSize:"20px", marginRight:"10px"}}/> 菜单
              </a>
            </Dropdown>            
            <Dropdown overlay={breadcrumbMenu()}>
              <a  className={styles.menuLink}>
                <Icon type="down" style={{fontSize:"20px", marginRight:"10px"}}/> 快速转到
              </a>
            </Dropdown>
        </Col>
        <Col  className={styles.searchBox} {...searchBarResponsiveStyle}  > 
          
<<<<<<< HEAD
          <Search size="default" placeholder="请输入搜索条件, 查找功能，数据和词汇解释,暂未实现" enterButton 
            style={{ marginLeft:"10px",marginTop:"7px",width:"100%"}} />
=======
          <Search size="default" placeholder="请输入搜索条件, 查找功能，数据和词汇解释，关闭请点击搜索结果空白处" 
            enterButton onFocus={()=>showSearchResult()} onChange={(evt)=>searchChange(evt)}
           	
            style={{ marginLeft:"10px",marginTop:"7px",width:"100%"}} />  
            
            
>>>>>>> ea67698ef1c4e94c89147baaf9f93aa768973fbe
          </Col>
          <Col  {...userBarResponsiveStyle}  > 
            <Dropdown overlay= { <TopMenu {...this.props} />} className={styles.right}>
                <a  className={styles.menuLink}>
                  <Icon type="user" style={{fontSize:"20px",marginRight:"10px"}}/> 账户
                </a>
            </Dropdown>
            
           </Col>  
         
         </Row>
        </Header>
       <Layout style={{  marginTop: 44 }}>
       
<<<<<<< HEAD
=======
      {this.state.showSearch&&(

        <div style={{backgroundColor:'black'}}  onClick={()=>hideSearchResult()}  >{searchLocalData(this.props.retailStoreOrderPicking,this.state.searchKeyword)}</div>

      )}
       
        
>>>>>>> ea67698ef1c4e94c89147baaf9f93aa768973fbe
         
         <Layout>
         
            
           <Content style={{ margin: '24px 24px 0', height: '100%' }}>
           
           {this.buildRouters()}
 
             
             
           </Content>
          </Layout>
        </Layout>
      </Layout>
     )
     return (
       <DocumentTitle title={this.getPageTitle()}>
         <ContainerQuery query={query}>
           {params => <div className={classNames(params)}>{layout}</div>}
         </ContainerQuery>
       </DocumentTitle>
     )
   }
}

export default connect(state => ({
  collapsed: state.global.collapsed,
  fetchingNotices: state.global.fetchingNotices,
  notices: state.global.notices,
  retailStoreOrderPicking: state._retailStoreOrderPicking,
  ...state,
}))(RetailStoreOrderPickingBizApp)



