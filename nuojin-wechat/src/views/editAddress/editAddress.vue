<template>
  <div>
    <div class="header">
      <div class="arrow ">
        <i class="iconfont icon-zuojiantou"></i>
      </div>
      <div class="headerTitle">编辑地址</div>
    </div>
    <div class="content">
      <ul>
        <li class="item">
          <span class="fl">收货人 :</span>
          <input
            type="text"
            placeholder="请输入收货人"
            :value="this.consignee"
          >
        </li>
        <li class="item">
          <span class="fl">手机号码 :</span>
          <input
            type="text"
            placeholder="请输入手机号码"
            :value="this.mobile"

          >
        </li>
        <li class="item">
          <span class="fl">所在地区 :</span>
          <!-- <input
            type="text"
            placeholder="请输入所在地区"
          >
          <i class="iconfont addresArrow icon-zuojiantou"></i>
          
           -->

            <!-- <div class="left">
                <span>所在地区</span>
            </div> -->
            <div class="right r">
                <div class="city" @click="toAddress">{{city}}</div>
                <i class="arrow-r"> </i>
            </div>
            <div class="div-wrap" >
              <div class="divwrap" v-show="mask">
                <v-distpicker type="mobile" @selected='selected' v-show="addInp">
                </v-distpicker>
              </div>
            </div>
            

           
        </li>

<div class="mask" ref="mask" v-show="mask"> </div>

        <li class="item">
          <span class="fl">详细地址 :</span>
          <textarea placeholder="请输入详细地址"></textarea>
        </li>
        <li class="item">
          <span class="fl">邮政编码 :</span>
          <input
            type="text"
            placeholder="请输入邮政编码"
          >
        </li>
      </ul>
    </div>
    <div class="btn" > 
      <button class="preservation" @click="Insert()">保存</button>
      <button class="delete" v-if="btnShowFlag">删除地址</button>
    </div>
  </div>
</template>
<script src="./editAddress.js"></script>

<style src="./editAddress.less" scoped="scoped" lang="less"></style>

<style>
    .divwrap{
        height: 400px;
        overflow-y: auto;
        position: fixed;
        left: 0;
        bottom: 0;
        width: 100%;
        z-index: 9;
    }
    .divwrap>>>.distpicker-address-wrapper{
        color: #999;
    }
    .divwrap>>>.address-header{
        position: fixed;
        bottom: 400px;
        width: 100%;
        background: #000;
        color:#fff;
    }
    .divwrap>>>.address-header ul li{
        flex-grow: 1;
        text-align: center;
    }
    .divwrap>>>.address-header .active{
        color: #fff;
        border-bottom:#666 solid 8px
    }
    .divwrap>>>.address-container .active{
        color: #000;
        
    }

</style>