Page({
    data: {
      teamList: [
        {
          messTitle: '1204应用开发小组',
          name: '潘怡',
          number: '2个',
          numberx: '3项'
        },
        {
          messTitle: '1204应用开发小组',
          name: '潘怡',
          number: '4个',
          numberx: '3项'
        }
      ],
      projectList: [
        {
          projectTitle: '多源老人居家监护系统',
          projectType: '竞赛',
          projectContent: '该项目巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴',
          projectGuide: '潘怡',
          development: '1204应用开发小组',
          process: '已结题',
          honor: '3项'
        },
        {
          projectTitle: '多源老人居家监护系统',
          projectType: '竞赛',
          projectContent: 'babababbabababababa',
          projectGuide: '潘怡',
          development: '1204机器人竞赛小组',
          process: '已结题',
          honor: '3项'
        },
        {
          projectTitle: '多源老人居家监护系统',
          projectType: '竞赛',
          projectContent: '该项目巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴拉巴',
          projectGuide: '潘怡',
          development: '1204应用开发小组',
          process: '已结题',
          honor: '3项'
        }
      ]
    },
    onLoad(options) {
  
    },
    /**
     * 生命周期函数--监听页面初次渲染完成
     */
    onReady() {
  
    },
    onShow() {
  
    },
    onHide() {
  
    },
    onUnload() {
  
    },
    // 成员信息跳转
    myTeamNavigateTo() {
      wx.navigateTo({
          url: './teamDetail/index'
      })
    },
    // 团队信息跳转
    myProjectNavigateTo() {
      wx.navigateTo({
          url: './projectDetail/index'
      })
    },
    // tabbar传值(适配iponeX底部tabbar)
    getIsIphoneX(e) {
      this.setData({
        isIphoneX: e.detail.isIphoneX
      })
    },
    /**
     * 页面相关事件处理函数--监听用户下拉动作
     */
    onPullDownRefresh() {
  
    },
    /**
     * 页面上拉触底事件的处理函数
     */
    onReachBottom() {
  
    },
    /**
     * 用户点击右上角分享
     */
    onShareAppMessage() {
  
    },
    /**
     * 页面滚动触发事件的处理函数
     */
    onPageScroll() {
  
    },
    /**
     * 页面尺寸改变时触发
     */
    onResize() {
  
    },
    /**
     * 当前是 tab 页时，点击 tab 时触发
     */
    onTabItemTap() {
  
    }
  })