<template>
  <el-card class="vote-card" shadow="hover">
    <h2>投票表單</h2>
    <el-form :model="voteData" :rules="createRules" ref="voteForm" @input="checkFormValid" >
      <el-form-item label="投票人" :label-width="100" prop="user">
        <el-input v-model="voteData.user" placeholder="請輸入投票人"></el-input>
      </el-form-item>
      <el-form-item label="投票選項" :label-width="100">
        <el-checkbox-group v-model="voteData.itemIds" class="vote-checkbox-group">
          <div class="vote-checkbox-item" v-for="item in voteSelect" :key="item.itemId">
            <el-checkbox :label="item.itemId">
              <span class="vote-item-name">{{ item.itemName }}</span>
            </el-checkbox>
          </div>
        </el-checkbox-group>
      </el-form-item>
      <div style="width: auto; text-align: center">
        <el-button type="primary" size="large" @click="submitVote" :disabled="!isFormValid">投票</el-button>
        <el-button type="default" size="large" @click="resetVoteForm">重置</el-button>
      </div>
    </el-form>
  </el-card>
</template>
<script>
import {ref, onMounted} from 'vue';
import {getVoteItems, submitVoteForm} from "@/utils/api.js";
import {ElMessage} from 'element-plus';

export default {
  name: 'VoteForm',
  setup() {
    const isFormValid = ref(false);
    const voteSelect = ref([]);
    const voteForm = ref(null);
    const voteData = ref({
      user: '',
      itemIds: []
    });

    const loadVoteSelect = async () => {
      try {
        const response = await getVoteItems();
        voteSelect.value = response.data;
      } catch (error) {
        ElMessage.error('無法取得投票項目');
      }
    }

    const submitVote = async () => {
      if (!voteData.value.user || voteData.value.itemIds.length === 0) {
        ElMessage.warning("請填寫使用者名稱並選擇至少一個投票項目");
        return;
      }
      try {
        await submitVoteForm({
          user: voteData.value.user,
          itemIds: voteData.value.itemIds
        });
        ElMessage.success('成功進行投票');
        await loadVoteSelect();
        resetVoteForm();
      } catch (error) {
        ElMessage.error('無法進行投票');
      }
    }

    const resetVoteForm = () => {
      voteData.value.user = '';
      voteData.value.itemIds = [];
    }

    const createRules = ref({
      user: [
        {
          required: true, message: '請輸入投票人', trigger: 'blur'
        },
        {
          min: 1, message: '投票人名稱不能少於 1 個字', trigger: 'blur'
        },
        {
          max: 64, message: '投票人名稱不能超過 64 個字', trigger: 'blur'
        },
        {
          validator: (rule, value, callback) => {
            const regex = /^(?!\s+$)[\u4e00-\u9fa5a-zA-Z0-9. ]+$/;
            if (regex.test(value)) {
              callback();
            } else {
              callback(new Error('投票人名稱只能包含中文、英文字母或數字'));
            }
          },
          trigger: 'blur',
        }
      ],
    });

    const checkFormValid = async () => {
      if (voteForm.value) {
        try {
          isFormValid.value = await voteForm.value.validate();
        } catch {
          isFormValid.value = false;
        }
      }
    };

    onMounted(() => {
      isFormValid.value = false;
      loadVoteSelect();
    });

    return {
      voteForm,
      voteData,
      voteSelect,
      loadVoteSelect,
      submitVote,
      resetVoteForm,
      createRules,
      isFormValid,
      checkFormValid
    }
  }
}
</script>
<style scoped>
.vote-card {
  width: 80%;
  max-width: 800px;
  padding: 20px;
  border-radius: 20px;
  background-color: #ffffff;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}

h2 {
  text-align: center;
  margin-bottom: 20px;
}

.el-form-item {
  margin-bottom: 20px;
}

.vote-checkbox-group {
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
}

.vote-checkbox-item {
  display: flex;
  align-items: center;
  background-color: #f9f9f9;
  padding: 8px 12px;
  border: 1px solid #e4e4e4;
  border-radius: 5px;
  box-shadow: 0 1px 3px rgba(0, 0, 0, 0.1);
  transition: background-color 0.3s, box-shadow 0.3s;
}

.vote-checkbox-item:hover {
  background-color: #f0f0f0;
  box-shadow: 0 2px 6px rgba(0, 0, 0, 0.2);
}

.vote-item-name {
  margin-left: 8px;
  font-weight: 500;
}
</style>
