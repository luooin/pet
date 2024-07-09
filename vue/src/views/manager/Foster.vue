<template>
  <div>
    <div class="search">
      <el-select v-model="status" placeholder="请选择状态" style="width: 200px; margin-left: 5px">
        <el-option label="待确认" value="待确认"></el-option>
        <el-option label="寄养中" value="寄养中"></el-option>
        <el-option label="已领回" value="已领回"></el-option>
      </el-select>
      <el-button type="info" plain style="margin-left: 10px" @click="load(1)">查询</el-button>
      <el-button type="warning" plain style="margin-left: 10px" @click="reset">重置</el-button>
    </div>

    <div class="operation" v-if="user.role === 'USER'">
      <el-button type="primary" plain @click="handleAdd">发布寄养</el-button>
    </div>

    <div class="table">
      <el-table :data="tableData" stripe  @selection-change="handleSelectionChange">
        <el-table-column prop="id" label="序号" width="80" align="center" sortable></el-table-column>
        <el-table-column prop="userName" label="用户" show-overflow-tooltip></el-table-column>
        <el-table-column prop="name" label="宠物昵称" show-overflow-tooltip></el-table-column>
        <el-table-column prop="time" label="寄养时间"></el-table-column>
        <el-table-column prop="days" label="寄养天数"></el-table-column>
        <el-table-column prop="status" label="寄养状态"></el-table-column>
        <el-table-column prop="roomName" label="寄养房间"></el-table-column>

        <el-table-column label="操作" width="180" align="center">
          <template v-slot="scope">
            <el-button plain type="primary" v-if="user.role === 'USER' && scope.row.status === '待确认'" @click="handleEdit(scope.row)" size="mini">编辑</el-button>
            <el-button plain type="danger" v-if="user.role === 'USER' && scope.row.status === '待确认'" size="mini" @click=del(scope.row.id)>撤回寄养</el-button>
            <el-button plain type="primary" v-if="user.role === 'ADMIN' && scope.row.status === '待确认'" size="mini" @click=roomInit(scope.row)>分配房间</el-button>
            <el-button plain type="primary" v-if="user.role === 'ADMIN' && scope.row.status === '寄养中'" size="mini" @click=back(scope.row)>领回</el-button>
          </template>
        </el-table-column>
      </el-table>

      <div class="pagination">
        <el-pagination
            background
            @current-change="handleCurrentChange"
            :current-page="pageNum"
            :page-sizes="[5, 10, 20]"
            :page-size="pageSize"
            layout="total, prev, pager, next"
            :total="total">
        </el-pagination>
      </div>
    </div>


    <el-dialog title="信息" :visible.sync="fromVisible" width="40%" :close-on-click-modal="false" destroy-on-close>
      <el-form label-width="100px" style="padding-right: 50px" :model="form" :rules="rules" ref="formRef">
        <el-form-item prop="name" label="宠物昵称">
          <el-input v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item prop="time" label="寄养时间">
          <el-date-picker style="width: 100%"
              v-model="form.time"
              type="date" value-format="yyyy-MM-dd"
              placeholder="选择日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item prop="days" label="寄养天数">
          <el-input v-model="form.days" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="fromVisible = false">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </div>
    </el-dialog>

    <el-dialog title="房间信息" :visible.sync="roomVisible" width="40%" :close-on-click-modal="false" destroy-on-close>
      <el-form label-width="100px" style="padding-right: 50px" :model="form">
        <el-form-item prop="roomId" label="选择房间">
          <el-select v-model="form.roomId" placeholder="请选择房间" style="width: 100%">
            <el-option v-for="item in roomData" :label="item.name" :value="item.id"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="roomVisible = false">取 消</el-button>
        <el-button type="primary" @click="fosterSave">确 定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
export default {
  name: "Foster",
  data() {
    return {
      tableData: [],  // 所有的数据
      pageNum: 1,   // 当前的页码
      pageSize: 10,  // 每页显示的个数
      total: 0,
      status: null,
      fromVisible: false,
      roomVisible: false,
      form: {},
      user: JSON.parse(localStorage.getItem('xm-user') || '{}'),
      rules: {

      },
      ids: [],
      roomData: []
    }
  },
  created() {
    this.load(1)
    this.loadRooms()
  },
  methods: {
    back(row) {
      this.form = JSON.parse(JSON.stringify(row))
      this.form.status = '已领回'
      this.save()
    },
    fosterSave() {
      this.form.status = '寄养中'
      this.$request.put('/foster/update', this.form).then(res => {
        if (res.code === '200') {
          this.$message.success('操作成功')
          this.roomVisible = false
          this.load(1)
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    loadRooms() {
      this.$request.get('/room/selectAllIdle').then(res => {
        if (res.code === '200') {
          this.roomData = res.data
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    roomInit(row) {
      this.form = JSON.parse(JSON.stringify(row))
      this.roomVisible = true;
    },
    handleAdd() {   // 新增数据
      this.form = { userId: this.user.id }  // 新增数据的时候清空数据
      this.fromVisible = true   // 打开弹窗
    },
    handleEdit(row) {   // 编辑数据
      this.form = JSON.parse(JSON.stringify(row))  // 给form对象赋值  注意要深拷贝数据
      this.fromVisible = true   // 打开弹窗
    },
    save() {   // 保存按钮触发的逻辑  它会触发新增或者更新
      this.$request({
        url: this.form.id ? '/foster/update' : '/foster/add',
        method: this.form.id ? 'PUT' : 'POST',
        data: this.form
      }).then(res => {
        if (res.code === '200') {  // 表示成功保存
          this.$message.success('保存成功')
          this.load(1)
          this.fromVisible = false
        } else {
          this.$message.error(res.msg)  // 弹出错误的信息
        }
      })
    },
    del(id) {   // 单个删除
      this.$confirm('您确定要撤回寄养吗？', '灵魂拷问', {type: "warning"}).then(response => {
        this.$request.delete('/foster/delete/' + id).then(res => {
          if (res.code === '200') {   // 表示操作成功
            this.$message.success('操作成功')
            this.load(1)
          } else {
            this.$message.error(res.msg)  // 弹出错误的信息
          }
        })
      }).catch(() => {
      })
    },
    handleSelectionChange(rows) {   // 当前选中的所有的行数据
      this.ids = rows.map(v => v.id)   //  [1,2]
    },
    delBatch() {   // 批量删除
      if (!this.ids.length) {
        this.$message.warning('请选择数据')
        return
      }
      this.$confirm('您确定批量删除这些数据吗？', '确认删除', {type: "warning"}).then(response => {
        this.$request.delete('/foster/delete/batch', {data: this.ids}).then(res => {
          if (res.code === '200') {   // 表示操作成功
            this.$message.success('操作成功')
            this.load(1)
          } else {
            this.$message.error(res.msg)  // 弹出错误的信息
          }
        })
      }).catch(() => {
      })
    },
    load(pageNum) {  // 分页查询
      if (pageNum) this.pageNum = pageNum
      this.$request.get('/foster/selectPage', {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          status: this.status,
        }
      }).then(res => {
        this.tableData = res.data?.list
        this.total = res.data?.total
      })
    },
    reset() {
      this.status = null
      this.load(1)
    },
    handleCurrentChange(pageNum) {
      this.load(pageNum)
    },
  }
}
</script>

<style scoped>

</style>
