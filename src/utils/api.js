import axios from "axios";

const apiService = axios.create({
    baseURL: import.meta.env.VITE_API_URL,
});
export const getVoteStats = async () => await apiService.get("/api/vote/stats");
export const getVoteItems = async () => await apiService.get("/api/vote/items");
export const submitVoteForm = async (data) => await apiService.post("/api/vote", data);
