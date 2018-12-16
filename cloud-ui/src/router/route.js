const index = r => require.ensure([], () => r(require('@/page/index')))
const home = r => require.ensure([], () => r(require('@/page/home')))
const baseTable = r => require.ensure([], () => r(require('@/page/baseTable')))
const baseForm = r => require.ensure([], () => r(require('@/page/baseForm')))




  let routes=[
    { path: '/', name: 'index', component: index,
      children:[
        { path:'home', name: 'home', component: home},
        { path:'baseTable', name: 'baseTable', component: baseTable},
        { path:'baseForm', name: 'baseForm', component: baseForm}
      ]
    },

  ]

export default  routes;
