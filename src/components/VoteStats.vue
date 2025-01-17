<template>
  <el-card class="vote-stats-card" shadow="hover">
    <h2>投票統計</h2>
    <el-table :data="statsData" border stripe style="width: 100%">
      <el-table-column prop="itemName" label="投票項目名稱"></el-table-column>
      <el-table-column prop="voteCount" label="投票數量" align="right"></el-table-column>
    </el-table>
  </el-card>
</template>
<script>
import {ref, onMounted, onBeforeUnmount } from "vue";
import axios from 'axios';
import {ElMessage} from 'element-plus';

export default {
  name: 'VoteStats',
  setup() {
    const statsData = ref(null);
    let intervalId = null;

    const loadVoteStats = async () => {
      try {
        const response = await axios.get(`${import.meta.env.VITE_API_URL}/api/vote/stats`);
        statsData.value = response.data;
      } catch (error) {
        ElMessage.error('無法取得投票統計資料');
      }
    }

    onMounted(() => {
      loadVoteStats();
      intervalId = setInterval(loadVoteStats, 3000);
    });

    onBeforeUnmount(() => {
      clearInterval(intervalId);
    });

    return {
      statsData,
      loadVoteStats,
    }
  }
}

</script>
<style scoped>
.vote-stats-card {
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

</style>
