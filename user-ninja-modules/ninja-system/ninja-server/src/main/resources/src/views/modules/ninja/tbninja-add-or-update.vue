<template>
  <el-dialog
    :title="!dataForm.id ? '新增' : '修改'"
    :close-on-click-modal="false"
    :visible.sync="visible">
    <el-form :model="dataForm" :rules="dataRule" ref="dataForm" @keyup.enter.native="dataFormSubmit()" label-width="80px">
    <el-form-item label="忍者名" prop="name">
      <el-input v-model="dataForm.name" placeholder="忍者名"></el-input>
    </el-form-item>
    <el-form-item label="忍者等级外键" prop="levelId">
      <el-input v-model="dataForm.levelId" placeholder="忍者等级外键"></el-input>
    </el-form-item>
    <el-form-item label="招募需求数量" prop="chip">
      <el-input v-model="dataForm.chip" placeholder="招募需求数量"></el-input>
    </el-form-item>
    <el-form-item label="碎片价格" prop="price">
      <el-input v-model="dataForm.price" placeholder="碎片价格"></el-input>
    </el-form-item>
    </el-form>
    <span slot="footer" class="dialog-footer">
      <el-button @click="visible = false">取消</el-button>
      <el-button type="primary" @click="dataFormSubmit()">确定</el-button>
    </span>
  </el-dialog>
</template>

<script>
  export default {
    data () {
      return {
        visible: false,
        dataForm: {
          id: 0,
          name: '',
          levelId: '',
          chip: '',
          price: ''
        },
        dataRule: {
          name: [
            { required: true, message: '忍者名不能为空', trigger: 'blur' }
          ],
          levelId: [
            { required: true, message: '忍者等级外键不能为空', trigger: 'blur' }
          ],
          chip: [
            { required: true, message: '招募需求数量不能为空', trigger: 'blur' }
          ],
          price: [
            { required: true, message: '碎片价格不能为空', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      init (id) {
        this.dataForm.id = id || 0
        this.visible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].resetFields()
          if (this.dataForm.id) {
            this.$http({
              url: this.$http.adornUrl(`/ninja/tbninja/info/${this.dataForm.id}`),
              method: 'get',
              params: this.$http.adornParams()
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.dataForm.name = data.tbNinja.name
                this.dataForm.levelId = data.tbNinja.levelId
                this.dataForm.chip = data.tbNinja.chip
                this.dataForm.price = data.tbNinja.price
              }
            })
          }
        })
      },
      // 表单提交
      dataFormSubmit () {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            this.$http({
              url: this.$http.adornUrl(`/ninja/tbninja/${!this.dataForm.id ? 'save' : 'update'}`),
              method: 'post',
              data: this.$http.adornData({
                'id': this.dataForm.id || undefined,
                'name': this.dataForm.name,
                'levelId': this.dataForm.levelId,
                'chip': this.dataForm.chip,
                'price': this.dataForm.price
              })
            }).then(({data}) => {
              if (data && data.code === 0) {
                this.$message({
                  message: '操作成功',
                  type: 'success',
                  duration: 1500,
                  onClose: () => {
                    this.visible = false
                    this.$emit('refreshDataList')
                  }
                })
              } else {
                this.$message.error(data.msg)
              }
            })
          }
        })
      }
    }
  }
</script>
