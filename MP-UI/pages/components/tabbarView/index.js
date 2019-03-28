// tabbar自定义
Component({
  /**
   * 组件的属性列表
   */
  properties: {
    activeIndex:{
      type: Number,
      value: 0
    }
  },

  /**
   * 组件的初始数据
   */
  data: {
    tabbarList: [
      {
        'name': '首页',
        'iconImg': 'iconIndex',
        'navigator': '../../pages/home/index'
      },
      {
        'name': '圈子',
        'iconImg': 'iconCircle',
        'navigator': '../../pages/forum/index'
      },
      {
        'name': '双创',
        'iconImg': 'iconShuangchuang',
        'navigator': '../../pages/doubleCreation/index'
      },
      {
        'name': '我的',
        'iconImg': 'iconMy',
        'navigator': '../../pages/personal/index'
      },
    ],
    isIphoneX: false
  },
  
  /**
   * 组件生命周期
   */
  lifetimes: {
    attached() {
      var that = this
      // 在组件实例进入页面节点树时执行
      wx.getSystemInfo({
        success: function (res) {
          console.log(res)
          if (res.model.indexOf('iPhone X') !== -1) {
            that.setData({
              isIphoneX: true
            })
            
          }
        }
      })
      // 组件传值
      var isIphoneX = that.data.isIphoneX;
      this.triggerEvent('getIsIphoneX', {isIphoneX})
    },
    detached() {
      // 在组件实例被从页面节点树移除时执行
    },
  },

  /**
   * 组件的方法列表
   */
  methods: {
    tabbarClick(e){
      let index = e.currentTarget.dataset.index;
      if(index !== this.properties.activeIndex) {
        wx.switchTab({
          url: this.data.tabbarList[index].navigator,
        });
      }     
    },
  }
})