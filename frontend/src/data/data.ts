import { IconType } from "react-icons";

export interface Category {
  title: string;
  Icon: IconType;
  style: string;
}

export interface Feature {
  title: string;
  description: string;
  Icon: IconType;
  style: string;
}
import {
  FaCode,
  FaShieldAlt,
  FaMobileAlt,
  FaCloud,
  FaTools,
  FaRobot,
  FaGamepad,
  FaBug,
  FaProjectDiagram,
} from "react-icons/fa";
import { Iinpform } from "../interfaces";
export const categories: Category[] = [
  {
    title: "Web Development",
    Icon: FaCode,
    style: "text-blue-600 w-6 h-6",
  },
  {
    title: "Cybersecurity",
    Icon: FaShieldAlt,
    style: "text-red-600 w-6 h-6",
  },
  {
    title: "Mobile Development",
    Icon: FaMobileAlt,
    style: "text-green-600 w-6 h-6",
  },

  {
    title: "Artificial Intelligence",
    Icon: FaRobot,
    style: "text-teal-600 w-6 h-6",
  },

  {
    title: "Game Development",
    Icon: FaGamepad,
    style: "text-purple-600 w-6 h-6",
  },
];
export const features: Feature[] = [
  {
    title: "Real-world Projects",
    description:
      "Let students apply knowledge in hands-on scenarios that simulate real job challenges.",
    Icon: FaProjectDiagram,
    style: "text-blue-600 w-6 h-6",
  },
  {
    title: "Skill Evaluation",
    description:
      "Assess technical and soft skills effectively through tailored projects and tasks.",
    Icon: FaTools,
    style: "text-yellow-500 w-6 h-6",
  },
  {
    title: "Scalable Platform",
    description:
      "Support thousands of users with a secure, reliable, and cloud-based system.",
    Icon: FaCloud,
    style: "text-sky-500 w-6 h-6",
  },
  {
    title: "Progress Tracking",
    description:
      "Monitor student development and project completion status with detailed analytics.",
    Icon: FaBug,
    style: "text-gray-700 w-6 h-6",
  },
];
export const slicer = (txt: string, length: number = 60) => {
  if (txt.length > length) {
    return `${txt.slice(0, length)}...`;
  }
  return txt;
};

interface Itags {
  Mcolor: string;
  txt: string;
}
export const tags: Itags[] = [
  {
    Mcolor: "bg-red-200 text-red-800",
    txt: "Java",
  },
  {
    Mcolor: "bg-green-700",
    txt: "Spring Boot",
  },
  {
    Mcolor: "bg-cyan-500",
    txt: "React",
  },
  {
    Mcolor: "bg-blue-800",
    txt: "Type Script",
  },
  {
    Mcolor: "bg-gray-500",
    txt: "Figma",
  },
];
export const inputData: Iinpform = {
  name: "nameSession",
  type: "text",
  placeholder: "Project Name",
  label: "Project Name",
};
export type CategoryType =
  | "Web Development"
  | "Cybersecurity"
  | "Game Development"
  | "Artificial Intelligence"
  | "Mobile Development";

export const CategoryColor = (Name: CategoryType) => {
  if (Name == "Web Development") return "bg-blue-100 text-blue-700";
  if (Name == "Cybersecurity") return "bg-red-100 text-red-700";
  if (Name == "Game Development") return "bg-purple-100 text-purple-700";
  if (Name == "Artificial Intelligence") return "bg-teal-100 text-teal-700";
  if (Name == "Mobile Development") return "bg-green-100 text-green-700";
};
