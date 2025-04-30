// sessionApi.ts

import { CookiesService, useAuthQuery } from "./imports.ts";

export const token = CookiesService.get("UserToken");

export const useCategories = () =>
  useAuthQuery({
    queryKey: ["CategoryData"],
    url: "/tech-skills/categories",
    config: {
      headers: { Authorization: `Bearer ${token}` },
    },
  });

export const useFields = () =>
  useAuthQuery({
    queryKey: ["fieldsData"],
    url: "/tech-skills/fields",
    config: {
      headers: { Authorization: `Bearer ${token}` },
    },
  });

export const useTechnologies = () =>
  useAuthQuery({
    queryKey: ["technologiesData"],
    url: "/tech-skills/technologies",
    config: {
      headers: { Authorization: `Bearer ${token}` },
    },
  });
